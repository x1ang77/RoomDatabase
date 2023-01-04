package com.caaron.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.R
import com.caaron.todolistfragment.databinding.FragmentAddItemBinding
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.viewModels.AddTaskViewModel


class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            viewModel.addTask(Task(null, title, date, details, 0))
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_add_item", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

}