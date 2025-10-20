package com.erickvazquezs.practicamodulo7.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.erickvazquezs.practicamodulo7.R
import com.erickvazquezs.practicamodulo7.application.PracticaModulo7Application
import com.erickvazquezs.practicamodulo7.databinding.FragmentMovieDetailsBinding
import com.erickvazquezs.practicamodulo7.databinding.FragmentMoviesListBinding
import com.erickvazquezs.practicamodulo7.utils.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch


private const val MOVIE_ID = "movie_id"
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            movieId = args.getInt(MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = (requireActivity().application as PracticaModulo7Application).repository
        Log.d(Constants.LOGTAG, "El id es $movieId")
        lifecycleScope.launch {
            try {
                val movie = repository.getMovie(movieId!!)
//                Log.d(Constants.LOGTAG, movie.toString())
                binding.tvTitle.text = movie.name
                binding.tvSynopsis.text = movie.synopsis
                binding.tvInformation.text = "${formatDuration(movie.duration)} • ${movie.year} • ${movie.genre}"

                Glide.with(binding.root.context)
                    .load(movie.poster)
                    .into(binding.ivPoster)

                val chipGroup: ChipGroup = binding.cgPlatforms

                movie.platforms.forEach { text ->
                    val chip = Chip(binding.root.context)
                    chip.text = text
                    chip.isClickable = false

                    chipGroup.addView(chip)
                }

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Error inesperado: ${e.printStackTrace()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
    }

    private fun formatDuration(decimalHours: Float): String {
        val totalMinutes = (decimalHours * 60).toInt()
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        return "${hours}h ${minutes}m"
    }
}