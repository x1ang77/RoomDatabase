package com.joel.todolistfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.joel.todolistfragments.MyApplication
import com.joel.todolistfragments.databinding.FragmentDetailsBinding
import com.joel.todolistfragments.viewModels.DetailsViewModel
import com.joel.todolistfragments.viewModels.MainViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels() {
        DetailsViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: DetailsFragmentArgs by navArgs()

        viewModel.getTaskById(navArgs.id)

        viewModel.task.observe(viewLifecycleOwner) {
            binding.run {
                tvTitle.text = it.title
                tvDate.text = it.date
                tvDetails.text = it.details
            }
        }

        binding.btnImageGallery.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsToImageGallery()
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteTask(navArgs.id)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsToEditItem(navArgs.id)
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.btnDetailsBackToHome.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        mainViewModel.refreshWords.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getTaskById(navArgs.id)
                mainViewModel.shouldRefreshWords(false)
            }
        }

        setFragmentResultListener("from_edit_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            mainViewModel.shouldRefreshWords(refresh)
        }
    }

}