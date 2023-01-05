package com.justin.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.justin.todolist.MyApplication
import com.justin.todolist.adapters.TaskAdapter
import com.justin.todolist.databinding.FragmentHomeBinding
import com.justin.todolist.viewModels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        viewModel.goToAddTask.asLiveData().observe(viewLifecycleOwner) {
            val action = HomeFragmentDirections.actionHomeToAddItem()
            NavHostFragment.findNavController(this).navigate(action)
        }
        viewModel.tasks.observe(viewLifecycleOwner) {
            binding.ivEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.setTasks(it)
        }

//        binding.fabAdd.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeToAddItem()
//            NavHostFragment.findNavController(this).navigate(action)
//        }

//        binding.btnNavigate.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeToImageGallery()
//            NavHostFragment.findNavController(this).navigate(action)
//        }

        setFragmentResultListener("from_home") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_edit_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_image_gallery") { _, result ->
            val msg = result.getString("greeting")
            Toast.makeText(requireActivity().applicationContext, msg, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(emptyList(), {
            if (it.id != null) {
                val action = HomeFragmentDirections.actionHomeToDetails(it.id)
                NavHostFragment.findNavController(this).navigate(action)
            }
        }, {
            viewModel.deleteTask(it.id!!)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_home", bundle)
            val action = HomeFragmentDirections.toHome()
            NavHostFragment.findNavController(this).navigate(action)
        })
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}

// File manager
// Gallery
// ViewPager2 with TabLayout