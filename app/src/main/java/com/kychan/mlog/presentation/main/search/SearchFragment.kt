package com.kychan.mlog.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val searchMovieAdapter by lazy {
        SearchMovieAdapter {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this

        setView()
        setViewModel()
        return binding.root
    }

    private fun setView() {
        with(binding) {
            searchViewModel.getSearchMovie("비포")
            rvMovie.adapter = searchMovieAdapter
        }
    }

    private fun setViewModel() {
        with(searchViewModel) {
            movieList.observe(viewLifecycleOwner, { movieList ->
                searchMovieAdapter.submitList(movieList)
            })
        }
    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }
}