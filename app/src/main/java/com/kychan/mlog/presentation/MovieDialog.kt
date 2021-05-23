package com.kychan.mlog.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kychan.mlog.R
import com.kychan.mlog.databinding.DialogMovieBinding

class MovieDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_movie, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    companion object {
        fun newInstance() =
            MovieDialog()
    }
}