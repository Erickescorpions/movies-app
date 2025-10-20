package com.erickvazquezs.practicamodulo7.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.erickvazquezs.practicamodulo7.R
import com.erickvazquezs.practicamodulo7.application.PracticaModulo7Application
import com.erickvazquezs.practicamodulo7.data.MovieRepository
import com.erickvazquezs.practicamodulo7.databinding.FragmentMoviesListBinding
import com.erickvazquezs.practicamodulo7.ui.adapters.MoviesAdapter
import kotlinx.coroutines.launch
import okio.IOException

class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as PracticaModulo7Application).repository
        lifecycleScope.launch {
            try {
                val movies = repository.getMovies()

                binding.moviesList.apply {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = MoviesAdapter(movies) { movie ->
                        requireActivity().supportFragmentManager.beginTransaction().replace(
                            R.id.container,
                            MovieDetailsFragment.newInstance(movie.id)
                        ).addToBackStack(null)
                            .commit()
                    }
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
}