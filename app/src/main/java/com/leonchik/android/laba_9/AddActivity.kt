package com.leonchik.android.laba_9

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
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
                film.date = LocalDate.of(s.toString().toInt(), 1, 1)
            }
        })
        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            filmRepository.addFilm(film)
            finish()
        }
    }
}