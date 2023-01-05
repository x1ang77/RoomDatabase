package com.justin.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.justin.todolist.MyApplication
import com.justin.todolist.R
import com.justin.todolist.data.models.Task
import com.justin.todolist.databinding.FragmentAddItemBinding
import com.justin.todolist.viewModels.EditTaskViewModel

class EditTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val viewModel: EditTaskViewModel by viewModels {
        EditTaskViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: EditTaskFragmentArgs by navArgs()
        viewModel.getTaskById(navArgs.id)

        viewModel.task.observe(viewLifecycleOwner) {
            binding.run {
                etTitle.setText(it.title)
                etDetails.setText(it.details)
                etDate.setText(it.date)
            }
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            viewModel.editTask(navArgs.id, Task(navArgs.id, title, date, details))

            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_edit_item", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}