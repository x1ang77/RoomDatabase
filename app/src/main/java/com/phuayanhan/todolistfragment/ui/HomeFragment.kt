package com.phuayanhan.todolistfragment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.phuayanhan.todolistfragment.MyApplication
import com.phuayanhan.todolistfragment.adapter.TaskAdapter
import com.phuayanhan.todolistfragment.databinding.FragmentHomeBinding
import com.phuayanhan.todolistfragment.model.Task
import com.phuayanhan.todolistfragment.viewModels.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels{
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    val tasks: MutableList<Task> = mutableListOf(
        Task(0, "Fix Bug1001", "02/12/22", "Bug in android app",0),
        Task(1, "Fix Bug1001", "02/12/22", "Bug in android app",0)
    )
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

//        adapter = TaskAdapter(tasks)
        binding.eFabAddNewItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.setTasks(it)
        }
//        binding.btnImageGallery.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToImageGalleryFragment()
//            NavHostFragment.findNavController(this).navigate(action)
//        }
        setFragmentResultListener("from_details"){_,result->
            val refresh=result.getBoolean("refresh")
            if(refresh){
                viewModel.getTasks()
            }
        }
        setFragmentResultListener("from_add_item"){_,result->
            val refresh=result.getBoolean("refresh")
            Log.d("refresh",refresh.toString())
            if(refresh){
                viewModel.getTasks()
            }
        }
        setFragmentResultListener("from_image_gallery"){ _, result ->
            val msg = result.getString("refresh")
            Toast.makeText(requireContext(), msg ,Toast.LENGTH_LONG).show()
        }
    }
    fun setupAdapter(){
        val layoutManager=LinearLayoutManager(requireContext())
        adapter= TaskAdapter(emptyList()){
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvTask.adapter=adapter
        binding.rvTask.layoutManager=layoutManager
    }
}