package com.leonchik.android.laba_9

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room.databaseBuilder
import database.FilmDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "film-database"
class FilmRepository private constructor(context: Context) {

    private val executor = Executors.newSingleThreadExecutor()
    private val database : FilmDatabase = databaseBuilder(
        context.applicationContext,
        FilmDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val filmDao = database.filmDao()

    fun getFilms(): LiveData<List<Film>> = filmDao.getFilms()
    fun getFilm(id: UUID): LiveData<Film?> = filmDao.getFilm(id)

    suspend fun delFilms() {
        filmDao.delFilms()
    }

    fun addFilm(film: Film) {
        executor.execute {
            filmDao.addFilm(film)
        }
    }

    fun updateFilm(film: Film) {
        executor.execute {
            filmDao.updateFilm(film)
        }
    }

    companion object {
        private var INSTANCE: FilmRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FilmRepository(context)
            }
        }
        fun get(): FilmRepository {
            return INSTANCE ?: throw
            IllegalStateException("CrimeRepository must be initialized")
        }
    }
}