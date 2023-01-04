package com.xiangze.todolistfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.todolistfragments.MyApplication
import com.xiangze.todolistfragments.R
import com.xiangze.todolistfragments.adapters.TaskAdapter
import com.xiangze.todolistfragments.databinding.FragmentDetailsBinding
import com.xiangze.todolistfragments.databinding.FragmentHomeBinding
import com.xiangze.todolistfragments.model.Task
import com.xiangze.todolistfragments.viewmodels.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }


    val tasks: MutableList<Task> = mutableListOf(
        Task(0, "Fix Bug1001", "02/12/22", "Bug in android app", 0),
        Task(1, "Fix Bug1002", "02/12/22", "Bug in android app", 0)
    )
//    val task = Task(0, "Fix Bug1001", "02/12/22", "Bug in android app", 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.setTasks(it)
        }

        binding.eFabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddItemFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_image_gallery") { _, result ->
            val msg = result.getString("greeting")
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }
    }
    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}

//student_id_yourname_projectname.zip

//Repository