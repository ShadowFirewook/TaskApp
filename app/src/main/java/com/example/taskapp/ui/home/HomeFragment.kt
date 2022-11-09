package com.example.taskapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.task.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(requireContext(), this::onLongClick,this::onCLick)
    }

    private fun onCLick(task: Task){
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK to task))
    }

       private  fun onLongClick(task: Task){
                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setTitle("Удалить?")
                alertDialog.setPositiveButton("Да"){dialog, _ ->
                    App.db.dao().delete(task)
                    setData()
                    dialog.dismiss()

                }
                alertDialog.setNegativeButton("Нет"){dialog, _ ->
                    dialog.dismiss()
                }
                alertDialog.create().show()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fub.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
        binding.taskResycler.adapter = adapter
        setData()
        }
    private fun setData(){
        val list = App.db.dao().getAllTask()
        adapter.addTasks(list.reversed())
       /* setFragmentResultListener(
            TASK
        ){
            _, result ->
            val task = result.getSerializable("task_key") as Task
            adapter.addTask(task)
        } */
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val TASK = "task_key"
    }
}