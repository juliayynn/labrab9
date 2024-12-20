package com.leonchik.android.laba_9

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.time.LocalDate
class AddActivity : AppCompatActivity() {

    private val filmRepository: FilmRepository = FilmRepository.get()
    lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        film = Film()
        val filmNameField: EditText = findViewById(R.id.film_name_field)
        val filmYearField: EditText = findViewById(R.id.film_year_field)

        filmNameField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                film.title = s.toString()
            }
        })

        filmYearField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                film.year = s.toString()
            }
        })

        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            filmRepository.addFilm(film)
            finish()
        }
    }

    private fun redrawPoster() {
        Picasso.get()
            .load(film.posterUrl)
            .placeholder(R.drawable.icon_film_placeholder)
            .into(findViewById<ImageView>(R.id.add_poster))
    }

}