package com.leonchik.android.laba_9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val TAG = "FilmChooseListFragment"
class FilmChooseListFragment: Fragment() {

    private lateinit var filmChooseViewModel: FilmChooseViewModel
    private lateinit var filmRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmChooseViewModel = ViewModelProvider(this)[FilmChooseViewModel::class.java]
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_film_choose, container, false)
        filmRecyclerView = view.findViewById(R.id.photo_recycler_view)
        this.filmRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmChooseViewModel.filmLiveData.observe(viewLifecycleOwner)
        { films ->
            filmRecyclerView.adapter = FilmAdapter(films)
        }
    }

    private inner class FilmHolder(view: View): RecyclerView.ViewHolder(view) {

        private lateinit var film: Film
        private val nameTextView: TextView = itemView.findViewById(R.id.choose_name)
        private val yearTextView: TextView = itemView.findViewById(R.id.choose_year)
        private val genreTextView: TextView = itemView.findViewById(R.id.choose_genre)
        private val posterImageView: ImageView = itemView.findViewById(R.id.choose_poster)

        fun bind(film: Film) {
            this.film = film
            nameTextView.text = this.film.title
            yearTextView.text = this.film.year
            genreTextView.text = this.film.genre
        }

        fun bindImage() {
            Picasso.get()
                .load(film.posterUrl)
                .placeholder(R.drawable.icon_film_placeholder)
                .into(posterImageView)
        }

    }

    private inner class FilmAdapter(var films: List<Film>): RecyclerView.Adapter<FilmHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
            val view =
                layoutInflater.inflate(R.layout.list_item_choose, parent, false)
            return FilmHolder(view)
        }

        override fun getItemCount() = films.size

        override fun onBindViewHolder(holder: FilmHolder, position: Int) {
            val film = films[position]
            holder.apply {
                holder.bind(film)
            }
            holder.bindImage()
        }
    }


    companion object {
        fun newInstance() = FilmChooseListFragment()
    }
}