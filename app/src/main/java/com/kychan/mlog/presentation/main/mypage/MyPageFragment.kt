package com.kychan.mlog.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentMyPageBinding
import com.kychan.mlog.presentation.main.MainActivity

class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding
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

    }

    private fun setView() {
        with(binding) {
            myMovieAdapter.submitList(
                listOf(
                    MyMovieItem("", "1", 1.0f),
                    MyMovieItem("", "2", 1.5f),
                    MyMovieItem("", "3", 2.0f),
                    MyMovieItem("", "4", 2.5f),
                    MyMovieItem("", "5", 3.0f),
                    MyMovieItem("", "6", 3.5f),
                    MyMovieItem("", "7", 4.0f),
                )
            )
            rvMovie.adapter = myMovieAdapter
            rvMovie.layoutManager = GridLayoutManager(context, 3)
        }
    }

    companion object {
        fun newInstance() =
            MyPageFragment()
    }
}