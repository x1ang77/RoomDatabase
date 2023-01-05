package com.chiaching.todolistfragments.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.chiaching.todolistfragments.MyApplication
import com.chiaching.todolistfragments.data.model.Task
import com.chiaching.todolistfragments.databinding.FragmentEditItemBinding
import com.chiaching.todolistfragments.viewModels.EditItemViewModel

class EditItemFragment : Fragment() {
    private lateinit var binding: FragmentEditItemBinding
    private lateinit var task: Task

    private val viewModel: EditItemViewModel by viewModels{
        EditItemViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditItemBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: EditItemFragmentArgs by navArgs()
        viewModel.getTaskById(navArgs.id)
        viewModel.task.observe(viewLifecycleOwner){
            binding.run {
                etTitle.setText(it.title)
                etDate.setText(it.date)
                etDetails.setText(it.details)
            }
        }

        binding.run{
            btnSave.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean("refresh",true)
                var task = Task(navArgs.id.toInt(),etTitle.text.toString(),etDate.text.toString(),etDetails.text.toString())
                viewModel.editTask(navArgs.id, task)
                setFragmentResult("from_details",bundle)
                NavHostFragment.findNavController(requireParentFragment()).popBackStack()
            }
        }

    }


}