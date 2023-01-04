package com.caaron.todolistfragment.ui

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
import com.caaron.todolist.adapters.TaskAdapter
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.databinding.FragmentHomeBinding
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.viewModels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    val tasks: MutableList<Task> = mutableListOf(
        Task(0, "Fix Bug1001", "02/12/22", "Bug in android a", 8)
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

        setUpAdapter()

//        adapter = TaskAdapter(tasks)
//        val layoutManager = LinearLayoutManager(requireActivity())
//        binding.rvTasks.adapter = adapter
//        binding.rvTasks.layoutManager = layoutManager


        binding.efabAddNewItem.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeToDetails(
//                "Greetings from Home Directory", "Justin"
//            )
            val action = HomeFragmentDirections.actionHomeToAddItem()
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.setTasks(it)
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

        setFragmentResultListener("from_image_gallery"){_, result->
            val msg = result.getString("greeting")
            Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()

        }
    }

    fun setUpAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(emptyList()) {
            if (it.id != null) {
                val action = HomeFragmentDirections.actionHomeToDetails(it.id!!)
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}