package com.nathalie.todolistfragments.fragments.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.databinding.FragmentAddItemBinding
import com.nathalie.todolistfragments.viewModels.AddViewModel

class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private var color: String = "#cedfd9"

    private val viewModel: AddViewModel by viewModels {
        AddViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.run {
            btnBrown.setOnClickListener {
                color = "#5f5449"
                wrapper.setCardBackgroundColor(Color.parseColor(color))
            }

            btnMauve.setOnClickListener {
                color = "#9b6a6c"
                wrapper.setCardBackgroundColor(Color.parseColor(color))
            }

            btnBlush.setOnClickListener {
                color = "#b09398"
                wrapper.setCardBackgroundColor(Color.parseColor(color))
            }

            btnMint.setOnClickListener {
                color = "#cedfd9"
                wrapper.setCardBackgroundColor(Color.parseColor(color))
            }

            btnIce.setOnClickListener {
                color = "#ebfcfb"
                wrapper.setCardBackgroundColor(Color.parseColor(color))
            }
        }


        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val details = binding.etDetails.text.toString()

            if (validate(title, details)) {
                viewModel.addTask(Note(null, title, details, color))
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_add_item", bundle)
                NavHostFragment.findNavController(this).popBackStack()
            } else {
                val snackbar =
                    Snackbar.make(binding.root, "Make sure to add something to the note!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }

    private fun validate(vararg list: String): Boolean {
        for (field in list) {
            if (field.isEmpty()) {
                return false
            }
        }
        return true
    }
}