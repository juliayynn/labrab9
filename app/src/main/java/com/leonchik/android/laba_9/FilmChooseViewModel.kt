package com.leonchik.android.laba_9

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FilmChooseViewModel: ViewModel() {

    var filmLiveData: LiveData<List<Film>> = OmdbFetcher().searchFilms("Harry", "2002")
    fun searchFilms(title: String = "", year: String) {
        filmLiveData = OmdbFetcher().searchFilms(title, year)
    }

}