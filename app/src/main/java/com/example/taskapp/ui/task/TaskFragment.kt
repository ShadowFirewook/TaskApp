package com.example.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.ui.home.HomeFragment

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener{
saveTask()
        }
    }
    private fun saveTask(){
        if (binding.etTitle.text?.isNotEmpty() == true){
            val task = Task(title = binding.etTitle.text.toString(),description = binding.etDescription.text.toString())
            setFragmentResult(
                HomeFragment.TASK, bundleOf("task_key" to task)
            )
            findNavController().navigateUp()
        }else{
            binding.etTitle.error = "Заполните название"
        }
    }
}