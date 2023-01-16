package com.caaron.todolistfragment.ui

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
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.R
import com.caaron.todolistfragment.databinding.FragmentDetailsBinding
import com.caaron.todolistfragment.viewModels.DetailsViewModel

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

        binding.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToUpdateItemFragment(navArgs.id)
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

//        Toast.makeText(requireActivity(), "${navArgs.greeting} ${navArgs.name}",Toast.LENGTH_LONG).show()
    }

}