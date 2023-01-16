package com.caaron.todolist.adapters

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caaron.todolistfragment.databinding.ItemLayoutTaskBinding
import com.caaron.todolistfragment.data.model.Task

class TaskAdapter(
    private var items: List<Task>,
    val onClick: (item: Task) -> Unit,
    val onLongClick: (item: Task) -> Unit,
    val onMoreClick: (view: View, item: Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

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
            item.image?.let{bytes ->
                val bitmap = BitmapFactory.decodeByteArray(item.image,0,bytes.size)
                image.setImageBitmap(bitmap)
            }
            cvTaskItem.setOnLongClickListener {
                onLongClick(item)
                return@setOnLongClickListener true
            }

            icMore.setOnClickListener {
                onMoreClick(it, item)
            }
        }
        Log.d("debugging", "inside onBindViewHolder $position")
    }

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
        this.items = items
    }

    class ItemTaskHolder(val binding: ItemLayoutTaskBinding) : RecyclerView.ViewHolder(binding.root)
}