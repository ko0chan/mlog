package com.kychan.mlog.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kychan.mlog.R
import com.kychan.mlog.databinding.FragmentSearchBinding
import com.kychan.mlog.presentation.main.MainActivity

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this

        setToolbar()

        return binding.root
    }

    private fun setToolbar() {
        (activity as MainActivity).supportActionBar?.run {
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.view_search)
        }
    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }
}