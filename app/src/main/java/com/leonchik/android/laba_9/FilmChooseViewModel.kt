package com.leonchik.android.laba_9

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
class FilmChooseViewModel: ViewModel() {
    val filmLiveData: LiveData<List<Film>> = OmdbFetcher().fetchContents()
}