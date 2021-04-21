package com.kychan.mlog.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentMyPageBinding
import com.kychan.mlog.presentation.main.MainActivity

class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding
    private val myPageViewModel by viewModels<MyPageViewModel>()
    private val myMovieAdapter by lazy {
        MyMovieAdapter {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
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
            myPageViewModel.getMyMovie()
            rvMovie.adapter = myMovieAdapter
            rvMovie.layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun setViewModel() {
        with(myPageViewModel) {
            movieList.observe(viewLifecycleOwner, {
                myMovieAdapter.submitList(it)
            })
        }
    }

    companion object {
        fun newInstance() =
            MyPageFragment()
    }
}