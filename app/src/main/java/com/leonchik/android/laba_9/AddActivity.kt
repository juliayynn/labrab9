package com.leonchik.android.laba_9

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class AddActivity : AppCompatActivity() {

    private lateinit var searchLauncher: ActivityResultLauncher<Intent>
    private val filmRepository: FilmRepository = FilmRepository.get()
    lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        searchLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                film = data?.getSerializableExtra("FILM") as Film
                refill()
            }
        }

        film = Film()
        val filmNameField: EditText = findViewById(R.id.film_name_field)
        val filmYearField: EditText = findViewById(R.id.film_year_field)

        filmNameField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (film.title != s.toString()) {
                    film.title = s.toString()
                }
            }
        })

        filmYearField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (film.year != s.toString()) {
                    film.year = s.toString()
                }
            }
        })

        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java).apply {
                putExtra("TITLE", film.title)
                putExtra("YEAR", film.year)
            }
            searchLauncher.launch(intent)
        }

        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            filmRepository.addFilm(film)
            finish()
        }
    }

    private fun refill() {
        if (film.posterUrl != "") {
            Picasso.get()
                .load(film.posterUrl)
                .into(findViewById<ImageView>(R.id.add_poster))
        }
        else
        {
            findViewById<ImageView>(R.id.add_poster).setImageResource(R.drawable.icon_film_placeholder)
        }
        val filmNameField: EditText = findViewById(R.id.film_name_field)
        val filmYearField: EditText = findViewById(R.id.film_year_field)
        filmNameField.setText(film.title)
        filmYearField.setText(film.year)
    }

}