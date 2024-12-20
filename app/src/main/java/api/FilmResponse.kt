package api

import com.google.gson.annotations.SerializedName
import com.leonchik.android.laba_9.Film

class FilmResponse {
    @SerializedName("Search")
    lateinit var films: List<Film>
}