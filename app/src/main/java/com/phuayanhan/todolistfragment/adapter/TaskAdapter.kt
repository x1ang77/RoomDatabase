package com.phuayanhan.todolistfragment.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phuayanhan.todolistfragment.databinding.ItemLayoutTaskBinding
import com.phuayanhan.todolistfragment.data.model.Task

class TaskAdapter(
    private var items:List<Task>,
):RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    var listener:Listener ?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(layoutInflater,parent,false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val item =items[position]
        holder.binding.run{
            tvTitle.text = item.title
            tvDate.text = item.date
            cvTaskItem.setOnClickListener{
                listener?.onClick(item)
            }
            cvTaskItem.setOnLongClickListener{
                listener?.onLongClick(item)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount()=items.size
    fun setTasks(items: List<Task>){
        this.items= items
    }

    class ItemTaskHolder(val binding: ItemLayoutTaskBinding):RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(task: Task);
        fun onLongClick(task:Task)

    }
}