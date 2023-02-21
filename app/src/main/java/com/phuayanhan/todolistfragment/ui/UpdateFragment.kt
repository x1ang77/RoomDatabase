package com.phuayanhan.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.phuayanhan.todolistfragment.MyApplication
import com.phuayanhan.todolistfragment.R
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.databinding.FragmentUpdateBinding
import com.phuayanhan.todolistfragment.viewModels.UpdateViewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    val viewModel: UpdateViewModel by viewModels{
        UpdateViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: DetailsFragmentArgs by navArgs()
        viewModel.task.observe(viewLifecycleOwner){
            binding.run{
                etTitle.setText(it.title)
                etDate.setText(it.date)
                etDetails.setText(it.detail)
            }
        }
        binding.btnSave.setOnClickListener {
            val title=binding.etTitle.text.toString()
            val date=binding.etDate.text.toString()
            val detail=binding.etDetails.text.toString()
            viewModel.UpdateTask(navArgs.id,Task(navArgs.id,title,date,detail,0))
            val bundle=Bundle()
            bundle.putBoolean("refresh",true)
            setFragmentResult("from_update_item",bundle)
            NavHostFragment.findNavController(this).popBackStack(R.id.homeFragment, inclusive = false)
        }
    }

}