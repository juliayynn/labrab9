package api

import retrofit2.Call
import retrofit2.http.GET

interface OmdbApi {

    @GET(
        "?apikey=ee8eee5f" +
                "&s=Harry" +
                "&y="
    )
    fun fetchContents(): Call<FilmResponse>

}