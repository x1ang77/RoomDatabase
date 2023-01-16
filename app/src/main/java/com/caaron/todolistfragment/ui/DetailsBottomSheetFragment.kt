package com.caaron.todolistfragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.databinding.FragmentDetailsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsBottomSheetFragment(val task: Task) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = task.title
        binding.tvDetails.text = task.details
    }


}