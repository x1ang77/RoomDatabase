package com.joel.todolistfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.joel.todolistfragments.MyApplication
import com.joel.todolistfragments.R
import com.joel.todolistfragments.databinding.FragmentAddItemBinding
import com.joel.todolistfragments.model.Task
import com.joel.todolistfragments.viewModels.AddTaskViewModel

class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddItem.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            viewModel.addTask(Task(null, title, date, details, 0))

            val bundle = Bundle()
//            bundle.putString("greeting", "Hello, greetings from AddItem")
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_add_item", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}