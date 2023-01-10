package com.justin.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.justin.todolist.R
import com.justin.todolist.data.models.Task
import com.justin.todolist.databinding.FragmentDetailsBottomSheetBinding

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