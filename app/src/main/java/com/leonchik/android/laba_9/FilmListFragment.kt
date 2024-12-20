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

class FilmListFragment: Fragment() {

    private var adapter: FilmAdapter? = null
    private lateinit var filmRecyclerView: RecyclerView
    private val filmListViewModel: FilmListViewModel by lazy {
        ViewModelProvider(this)[FilmListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film_list, container, false)
        this.filmRecyclerView = view.findViewById(R.id.film_recycler_view)
        this.filmRecyclerView.layoutManager = LinearLayoutManager(context)

        this.filmRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmListViewModel.filmListLiveData.observe(viewLifecycleOwner)
        { films -> films?.let {
                updateUI(films)
            }
        }
    }

    private fun updateUI(films: List<Film>) {
        adapter = FilmAdapter(films)
        this.filmRecyclerView.adapter = adapter
    }

    private inner class FilmHolder(view: View): RecyclerView.ViewHolder(view) {
        private lateinit var film: Film
        private val nameTextView: TextView = itemView.findViewById(R.id.film_name)
        private val posterImageView: ImageView = itemView.findViewById(R.id.film_poster)
        private val yearTextView: TextView = itemView.findViewById(R.id.film_year)

        fun bind(film: Film) {
            this.film = film
            nameTextView.text = this.film.title
            yearTextView.text = this.film.year
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
                layoutInflater.inflate(R.layout.list_item_film, parent, false)
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
        fun newInstance(): FilmListFragment {
            return FilmListFragment()
        }
    }
}