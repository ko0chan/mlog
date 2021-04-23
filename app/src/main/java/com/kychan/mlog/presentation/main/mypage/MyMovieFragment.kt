package com.kychan.mlog.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentMyMovieBinding
import com.kychan.mlog.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyMovieFragment : Fragment() {

    private lateinit var binding: FragmentMyMovieBinding
    private val myMovieViewModel by viewModels<MyMovieViewModel>()
    private val myMovieAdapter by lazy {
        MyMovieAdapter {
            myMovieViewModel.deleteMovie(it.link)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_movie, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = "영찬"
        setView()
        setViewModel()

    }

    private fun setView() {
        with(binding) {
            myMovieViewModel.getMovieAll()
            rvMovie.adapter = myMovieAdapter
            rvMovie.layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun setViewModel() {
        with(myMovieViewModel) {
            movieList.observe(viewLifecycleOwner, {
                myMovieAdapter.submitList(it.map { movie ->
                    movie.toMovieItem()
                })
            })
        }
    }

    companion object {
        fun newInstance() =
            MyMovieFragment()
    }
}