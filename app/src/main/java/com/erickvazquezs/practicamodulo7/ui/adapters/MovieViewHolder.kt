package com.erickvazquezs.practicamodulo7.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erickvazquezs.practicamodulo7.data.remote.model.MovieDto
import com.erickvazquezs.practicamodulo7.databinding.MovieItemBinding

class MovieViewHolder(
    private val binding: MovieItemBinding,
    private val onClick: (MovieDto) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    private var currentItem: MovieDto? = null

    // esto se ejecuta al crear el view holder
    init {
        binding.root.setOnClickListener {
            currentItem?.let(onClick)
        }
    }

    // este metodo se ejecuta cada vez que el recycler view
    // se va a mostrar en pantalla
    fun bind(movie: MovieDto) {
        currentItem = movie

        binding.tvMovieName.text = movie.name
        Glide.with(binding.root.context)
            .load(movie.poster)
            .into(binding.poster)
    }

    // metodo estatico para crear MovieViewHolder
    companion object {
        fun create(
            parent: ViewGroup,
            onClick: (MovieDto) -> Unit
        ): MovieViewHolder {
            val binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return MovieViewHolder(binding, onClick)
        }
    }
}