package com.leonchik.android.laba_9

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "FilmChooseListFragment"
class FilmChooseListFragment: Fragment() {

    private lateinit var filmRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val omdbLiveData: LiveData<List<Film>> = OmdbFetcher().fetchContents()
        omdbLiveData.observe(
            this
        ) { films ->
            Log.d(TAG, "Response received: $films")
        }
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

    companion object {
        fun newInstance() = FilmChooseListFragment()
    }
}