package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    fun searchFilms(@Query("s") title: String, @Query("y") year: String): Call<FilmResponse>

}