package com.chiaching.todolistfragments.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.chiaching.todolistfragments.MyApplication
import com.chiaching.todolistfragments.R
import com.chiaching.todolistfragments.databinding.FragmentDetailsBinding
import com.chiaching.todolistfragments.viewModels.DetailsViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    val viewModel: DetailsViewModel by viewModels{
        DetailsViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: DetailsFragmentArgs by navArgs()

        binding.btnImageGallery.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToImageGalleryFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }


        viewModel.getTaskById(navArgs.id)

        viewModel.task.observe(viewLifecycleOwner){
            binding.run{
                tvTitle.text = it.title
                tvDate.text = it.date
                tvDetails.text = it.details
            }
        }

        binding.btnDelete.setOnClickListener{
            viewModel.deleteTask(navArgs.id)
            val bundle = Bundle()
            bundle.putBoolean("refresh",true)
            setFragmentResult("from_details",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnBack.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("greeting","Hello, Greeting from details")
            setFragmentResult("from_details",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}