package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leonchik.android.laba_9.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getFilms(): LiveData<List<Film>>

    @Query("DELETE FROM film WHERE watched = 1")
    suspend fun delFilms()

    @Insert
    fun addFilm(film: Film)

    @Update
    fun updateFilm(film: Film)

}