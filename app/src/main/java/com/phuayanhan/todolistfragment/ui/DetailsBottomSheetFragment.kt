package com.phuayanhan.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.phuayanhan.todolistfragment.R
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.databinding.FragmentDetailsBinding
import com.phuayanhan.todolistfragment.databinding.FragmentDetailsBottomSheetBinding

class DetailsBottomSheetFragment(val task:Task) : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentDetailsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text =task.title
        binding.tvDetails.text =task.detail
    }
}