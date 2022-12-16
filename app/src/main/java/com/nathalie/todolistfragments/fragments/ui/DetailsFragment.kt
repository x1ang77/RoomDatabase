package com.nathalie.todolistfragments.fragments.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.databinding.FragmentDetailsBinding
import com.nathalie.todolistfragments.viewModels.DetailsViewModel


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    val navArgs: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: DetailsFragmentArgs by navArgs()


        viewModel.getNoteById(navArgs.id)

        viewModel.note.observe(viewLifecycleOwner) {
            binding.run {
                if(it.color == "#5f5449" || it.color == "#9b6a6c" || it.color == "#b09398") {
                    tvTitle.setTextColor(Color.parseColor("#ffffff"))
                    tvDetails.setTextColor(Color.parseColor("#ffffff"))
                }

                tvTitle.text = it.title
                tvDetails.text = it.details
                root.setBackgroundColor(Color.parseColor(it.color))
            }
        }


        binding.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToEditFragment(navArgs.id)
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}