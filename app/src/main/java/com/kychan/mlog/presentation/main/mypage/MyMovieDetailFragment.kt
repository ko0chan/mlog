package com.kychan.mlog.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentMyMovieDetailBinding
import com.kychan.mlog.presentation.main.MainActivity

class MyMovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMyMovieDetailBinding
    private val myMovieDetailViewModel by viewModels<MyMovieDetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_movie_detail, container, false)
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
        }
    }

    private fun setViewModel() {
        with(myMovieDetailViewModel) {
        }
    }

    companion object {
        fun newInstance() =
            MyMovieDetailFragment()
    }
}