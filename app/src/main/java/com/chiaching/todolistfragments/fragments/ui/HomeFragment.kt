package com.chiaching.todolistfragments.fragments.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chiaching.todolistfragments.MyApplication
import com.chiaching.todolistfragments.adapters.TaskAdapter
import com.chiaching.todolistfragments.databinding.FragmentHomeBinding
import com.chiaching.todolistfragments.model.Task
import com.chiaching.todolistfragments.viewModels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

//    val tasks: MutableList<Task> = mutableListOf(
//        Task(0, "Fix Bug1001", "02/12/22", "Bug in android app", 0),
//        Task(1, "Fix Bug1001", "02/12/22", "Bug in android app", 0)
//    )
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
        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.setTasks(it)
        }
        binding.eFabAddNewItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddItemFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        setFragmentResultListener("from_details"){_, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh){
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_add_item"){_, result ->
            val refresh = result.getBoolean("refresh")
            Log.d("tryDebug",refresh.toString())
            if(refresh){
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_image_gallery") {_, result ->
            val msg = result.getString("refresh")
            Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
        }
    }

    fun setupAdapter(){
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(emptyList()){
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = layoutManager
    }
}