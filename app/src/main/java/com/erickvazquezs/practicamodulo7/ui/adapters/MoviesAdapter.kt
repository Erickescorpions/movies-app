package com.erickvazquezs.practicamodulo7.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erickvazquezs.practicamodulo7.data.remote.model.MovieDto

class MoviesAdapter(
    private val movies: List<MovieDto>,
    private val onClick: (MovieDto) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}