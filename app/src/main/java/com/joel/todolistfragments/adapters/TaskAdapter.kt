package com.joel.todolistfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joel.todolistfragments.databinding.ItemTaskLayoutBinding
import com.joel.todolistfragments.data.model.Task

class TaskAdapter(private var items: List<Task>, val onClick: (item: Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    } //create UI element

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val item = items[position]
        holder.binding.run {
            tvTitle.text = item.title
            tvDate.text = item.date
            cvTaskItem.setOnClickListener {
                onClick(item)
            }
        }
    } // bind data to UI above

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemTaskHolder(val binding: ItemTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}