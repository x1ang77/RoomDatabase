package com.nathalie.todolistfragments.fragments.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.R
import com.nathalie.todolistfragments.databinding.FragmentEditBinding
import com.nathalie.todolistfragments.viewModels.EditViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var color: String
    val viewModel: EditViewModel by viewModels {
        EditViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: EditFragmentArgs by navArgs()

        viewModel.getNoteById(navArgs.id)

        viewModel.note.observe(viewLifecycleOwner) {
            binding.run {
                color = it.color.toString()
                etTitle.setText(it.title)
                etDetails.setText(it.details)
                wrapper.setCardBackgroundColor(Color.parseColor(it.color))
            }
        }

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
            val id = navArgs.id
            val title = binding.etTitle.text.toString()
            val details = binding.etDetails.text.toString()

            if (validate(title, details)) {
                val note = Note(id, title, details, color)
                viewModel.updateNote(navArgs.id, note)
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_edit", bundle)

                NavHostFragment.findNavController(this).popBackStack()
            } else {
                val snackbar =
                    Snackbar.make(
                        binding.root,
                        "Make sure to add something to the note!",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.show()
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteNote(navArgs.id)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)

            NavHostFragment.findNavController(this).popBackStack()

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