package com.xiangze.todolistfragments.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.todolistfragments.databinding.ItemLayoutTaskBinding
import com.xiangze.todolistfragments.model.Task

class TaskAdapter(
     var items: List<Task>,
     val onClick:(item: Task) -> Unit
): RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val item = items[position]
        holder.binding.run {
            tvTitle.text = item.title
            tvDate.text = item.date
            cvTaskItem.setOnClickListener {
                onClick(item)
            }
        }
        Log.d("debugging", "inside onBindViewHolder $position")
    }

    override fun getItemCount() = items.size

    fun setTasks(tasks: List<Task>) {
        items = tasks
        notifyDataSetChanged()
    }

    class ItemTaskHolder(val binding: ItemLayoutTaskBinding): RecyclerView.ViewHolder(binding.root)
}