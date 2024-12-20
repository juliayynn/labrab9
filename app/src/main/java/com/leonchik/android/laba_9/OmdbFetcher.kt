package com.leonchik.android.laba_9

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import api.FilmInterceptor
import api.FilmResponse
import api.OmdbApi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "OmdbFetcher"
class OmdbFetcher {

    private val omdbApi: OmdbApi
    init {

        val client = OkHttpClient.Builder()
            .addInterceptor(FilmInterceptor())
            .build()

        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl("https://omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        omdbApi = retrofit.create(OmdbApi::class.java)

    }

    fun searchFilms(title: String, year: String): LiveData<List<Film>> {
        return fetchFilmMetadata(omdbApi.searchFilms(title, year))
    }

    private fun fetchFilmMetadata(filmRequest: Call<FilmResponse>): LiveData<List<Film>> {

        val responseLiveData: MutableLiveData<List<Film>> = MutableLiveData()

        filmRequest.enqueue(object: Callback<FilmResponse> {

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch films", t)
            }

            override fun onResponse(
                call: Call<FilmResponse>,
                response: Response<FilmResponse>
            ) {
                Log.d(TAG, "Response received")
                var films: List<Film> = response.body()?.films?: mutableListOf()
                films = films.filterNot {
                    it.posterUrl.isBlank()
                }
                responseLiveData.value = films
            }
        })

        return responseLiveData
    }

}