package com.leonchik.android.laba_9

import androidx.lifecycle.ViewModel

class FilmListViewModel: ViewModel() {
    val films = mutableListOf<Film>()
    init {
        for (i in 0 until 100) {
            val film = Film()
            film.title = "Film #$i"
            film.watched = i % 2 == 0
            films += film
        }
    }
}