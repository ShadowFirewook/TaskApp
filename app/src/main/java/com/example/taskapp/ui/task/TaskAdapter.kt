package com.example.taskapp.ui.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(private val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = arrayListOf<Task>()



    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
            if (adapterPosition % 2 == 0){
                binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.white))
            }else{
                binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.black))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.black))
            }
            var item = tasks[adapterPosition]
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun addTask(task: Task){
        tasks.add(0,task)
        notifyItemChanged(0)
    }

}