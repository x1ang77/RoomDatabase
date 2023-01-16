package com.caaron.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.R
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.databinding.FragmentUpdateItemBinding
import com.caaron.todolistfragment.viewModels.AddTaskViewModel
import com.caaron.todolistfragment.viewModels.UpdateViewModel

class UpdateItemFragment : Fragment() {
    private lateinit var binding: FragmentUpdateItemBinding
    private val viewModel: UpdateViewModel by viewModels {
        UpdateViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: UpdateItemFragmentArgs by navArgs()
        viewModel.getTaskById(navArgs.id)
        viewModel.task.observe(viewLifecycleOwner) {
            binding.run {
                etTitle.setText(it.title)
                etDate.setText(it.date)
                etDetails.setText(it.details)
            }
        }
        binding.btnConfirm.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            val task = Task(navArgs.id, title, date, details)
            viewModel.updateTask(navArgs.id, task)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_edit", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

}