package com.example.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.ui.home.HomeFragment

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var isEmptyData = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = arguments?.getSerializable(HomeFragment.TASK) as Task?

        isEmptyData = task == null

        if (isEmptyData){
            binding.btnAdd.text = "add"
        }else
            binding.btnAdd.text = "update"

        binding.etTitle.setText(task?.title)
        binding.etDescription.setText(task?.description)

        binding.btnAdd.setOnClickListener{

            if (isEmptyData){
                saveTask()
            }else
                updateTask(task)

        }
    }

    private fun updateTask(task: Task?) {
        if (binding.etTitle.text?.isNotEmpty() == true) {
            task?.title = binding.etTitle.text.toString()
            task?.description = binding.etDescription.text.toString()
            if (task != null) {
                App.db.dao().update(task)
            }
            findNavController().navigateUp()
        }
    }

    fun saveTask(){
        if (binding.etTitle.text?.isNotEmpty() == true){
            val task = Task(title = binding.etTitle.text.toString(),description = binding.etDescription.text.toString())
            App.db.dao().insert(task)
            findNavController().navigateUp()
        }else{
            binding.etTitle.error = "Заполните название"
        }
    }
}