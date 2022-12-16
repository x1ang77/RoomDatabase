package com.nathalie.todolistfragments.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.adapters.NoteAdapter
import com.nathalie.todolistfragments.databinding.FragmentHomeBinding
import com.nathalie.todolistfragments.viewModels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: NoteAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()

        binding.efabAddNewItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddItem()
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getNotes()
            }
        }

        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getNotes()
            }
        }

        setFragmentResultListener("from_edit") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getNotes()
            }
        }
    }

    fun setUpAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = NoteAdapter(emptyList()) {
            val action = HomeFragmentDirections.actionHomeToDetails(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = layoutManager
    }
}