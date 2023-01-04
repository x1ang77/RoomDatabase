package com.xiangze.todolistfragments.ui

import      android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.xiangze.todolistfragments.MyApplication
import com.xiangze.todolistfragments.R
import com.xiangze.todolistfragments.databinding.FragmentDetailsBinding
import com.xiangze.todolistfragments.viewmodels.DetailsViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        viewModel.task.observe(viewLifecycleOwner) {
            binding.run {
                tvGreeting.text = it.title
                tvDate.text = it.date
                tvDetails.text = it.details
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteTask(navArgs.id)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

//        Toast.makeText(requireActivity(), "${navArgs.greeting} ${navArgs.name}", Toast.LENGTH_LONG).show()
    }
}