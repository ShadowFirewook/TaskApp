package com.example.taskapp.ui.onBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.R
import com.example.taskapp.data.OnBoard
import com.example.taskapp.databinding.ItemOnboardBinding
import com.example.taskapp.loadImage

class OnBoardingAdapter(
private val onClick:() -> Unit
): Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val array = arrayListOf(
        OnBoard(title = "Планировщик задач","Поможет написать список задач", R.raw.anim_task_1),
        OnBoard(title = "Напоминания","Напомнит сделать дела в заданное вами время",R.raw.anim_task_2),
        OnBoard(title = "Удобство","Легкий и интуитивный в использовании",R.raw.anim_task_3)
    )

    inner class OnBoardingViewHolder(private var binding: ItemOnboardBinding) : ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard){
            binding.tvTitle.text = onBoard.title
            binding.tvDescription.text = onBoard.description
            onBoard.image?.let { binding.ivBoard.setAnimation(it) }
            binding.btnStart.isVisible = adapterPosition == array.lastIndex
            binding.tvSkip.isVisible = adapterPosition != array.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }

            binding.tvSkip.setOnClickListener {
                onClick()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(array[position])
    }

    override fun getItemCount() = array.size

}


