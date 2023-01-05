package com.chiaching.todolistfragments.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.chiaching.todolistfragments.MyApplication
import com.chiaching.todolistfragments.databinding.FragmentAddItemBinding
import com.chiaching.todolistfragments.data.model.Task
import com.chiaching.todolistfragments.viewModels.AddTaskViewModel

class AddItemFragment : Fragment() {

    private lateinit var binding : FragmentAddItemBinding
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            viewModel.addTask(Task(null,title,date,details))
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_add_item",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}