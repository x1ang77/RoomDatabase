package com.joel.todolistfragments.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.joel.todolistfragments.MainActivity
import com.joel.todolistfragments.MyApplication
import com.joel.todolistfragments.R
import com.joel.todolistfragments.adapters.TaskAdapter
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.databinding.FragmentHomeBinding
import com.joel.todolistfragments.viewModels.HomeViewModel
import com.joel.todolistfragments.viewModels.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels() {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }
    private lateinit var adapter: TaskAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        mainViewModel.refreshWords.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getTasks()
                mainViewModel.shouldRefreshWords(false)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.setTasks(it)
//            adapter = TaskAdapter(tasks)
//            val layoutManager = LinearLayoutManager(requireActivity())
//            binding.rvItems.adapter = adapter
//            binding.rvItems.layoutManager = layoutManager
        }

        binding.fabAddNewItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddItem()
            NavHostFragment.findNavController(this).navigate(action)
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            mainViewModel.shouldRefreshWords(refresh)
        }

        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            mainViewModel.shouldRefreshWords(refresh)

        }
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(emptyList())
        adapter.listener = object : TaskAdapter.Listener {
            override fun onClick(task: Task) {
                val action = HomeFragmentDirections.actionHomeToDetails(task.id!!)
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }

            override fun onLongClick(task: Task) {
                val detailsFragment = DetailsBottomSheetFragment(task)
                detailsFragment.show(childFragmentManager, "Child-Fragment")
            }

            override fun onMoreClick(view: View, task: Task) {
                val popupMenu = PopupMenu(requireContext(), view)
                popupMenu.setOnMenuItemClickListener {
                    return@setOnMenuItemClickListener when (it.itemId) {
                        R.id.action1 -> {
                            Log.d("debugging", "Action 1: ${task.title}")
                            true
                        }
                        R.id.action2 -> {
                            Log.d("debugging", "Action 2: ${task.title}")
                            true
                        }
                        R.id.action3 -> {
                            Log.d("debugging", "Action 3: ${task.title}")
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.inflate(R.menu.task_actions)
                popupMenu.setForceShowIcon(true)
                popupMenu.show()
            }
        }
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = layoutManager
    }
}