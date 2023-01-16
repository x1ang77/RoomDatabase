package com.caaron.todolistfragment.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caaron.todolist.adapters.TaskAdapter
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.R
import com.caaron.todolistfragment.databinding.FragmentHomeBinding
import com.caaron.todolistfragment.data.model.Task
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

        setFragmentResultListener("from_edit") { _, result ->
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

    @RequiresApi(Build.VERSION_CODES.Q)
    fun setUpAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(
            emptyList(),
            {
                val action = HomeFragmentDirections.actionHomeToDetails(it.id!!)
                NavHostFragment.findNavController(this).navigate(action)
            },
            {
                val detailsFragment = DetailsBottomSheetFragment(it)
                detailsFragment.show(childFragmentManager,"Child-Fragment")
            },
            { view, task ->
                val popupMenu = PopupMenu(requireContext(), view)
                popupMenu.setOnMenuItemClickListener {
                    return@setOnMenuItemClickListener when(it.itemId){
                        R.id.action1 ->{
                            Log.d("debug","Action 1 :${task.title}")
                            true
                        }
                        R.id.action2 ->{
                            Log.d("debug","Action 2 :${task.title}")
                            true
                        }
                        R.id.action3 ->{
                            Log.d("debug","Action 3 :${task.title}")
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.inflate(R.menu.task_actions)
                popupMenu.setForceShowIcon(true)
                popupMenu.show()
            }
        )
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}