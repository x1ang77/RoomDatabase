package com.phuayanhan.todolistfragment.ui

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
import com.phuayanhan.todolistfragment.MyApplication
import com.phuayanhan.todolistfragment.R
import com.phuayanhan.todolistfragment.databinding.FragmentDetailsBinding
import com.phuayanhan.todolistfragment.viewModels.DetailsViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    val viewModel:DetailsViewModel by viewModels{
        DetailsViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details, container, false)
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

                tvTitle.text=it.title
                tvDate.text=it.date
                tvDetails.text=it.detail
            }
        }
        binding.btnBack.setOnClickListener{
            viewModel.deleteTask(navArgs.id)
            val bundle=Bundle()
            bundle.putBoolean("refresh",true)
            setFragmentResult("from_details",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToUpdateFragment(navArgs.id)
            NavHostFragment.findNavController(this).navigate(action)
        }

    }
}





















