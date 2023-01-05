package com.joel.todolistfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.joel.todolistfragments.MyApplication
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.databinding.FragmentAddItemBinding
import com.joel.todolistfragments.viewModels.DetailsViewModel
import com.joel.todolistfragments.viewModels.EditItemViewModel
import com.joel.todolistfragments.viewModels.HomeViewModel

class EditItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val viewModel: EditItemViewModel by viewModels() {
        EditItemViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
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
        val navArgs: EditItemFragmentArgs by navArgs()

        viewModel.getTaskById(navArgs.id)

        viewModel.task.observe(viewLifecycleOwner) {
            binding.run {
                etTitle.setText(it.title)
                etDate.setText(it.date)
                etDetails.setText(it.details)
                btnAddItem.text = "Edit"
            }
        }

        binding.btnAddItem.setOnClickListener {
            val id = navArgs.id
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            val item = Task(id, title, date, details, 0)
            viewModel.updateTask(id, item)

            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_edit_item", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}