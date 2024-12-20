package com.leonchik.android.laba_9

import androidx.lifecycle.ViewModel

class FilmListViewModel: ViewModel() {
    private val filmRepository = FilmRepository.get()
    val filmListLiveData = filmRepository.getFilms()
}