package com.justin.todolist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justin.todolist.databinding.TaskItemLayoutBinding
import com.justin.todolist.data.models.Task

// Recycler view displays a list of data dynamically
class TaskAdapter(
    private var items: List<Task>,
    val onClick: (item: Task) -> Unit,
    val onLongClick: (item: Task) -> Unit,
    val onMoreClick: (view: View, item: Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {
    // this creates the UI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    // this binds the data to the UI
    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val item = items[position]
        holder.binding.run {
            tvTitle.text = item.title
            tvDate.text = item.date
            cvTaskItem.setOnClickListener {
                onClick(item)
            }
            cvTaskItem.setOnLongClickListener {
                onLongClick(item)
//                return@setOnLongClickListener true
                true
            }
            ibMore.setOnClickListener {
                onMoreClick(it, item)
            }
        }
        Log.d("debugging", "Inside onBindViewHolder $position")
    }

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
        this.items = items
        notifyDataSetChanged()
    }

    // single unit of recycler view
    class ItemTaskHolder(val binding: TaskItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}