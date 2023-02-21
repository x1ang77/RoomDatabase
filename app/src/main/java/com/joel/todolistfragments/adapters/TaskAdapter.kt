package com.joel.todolistfragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joel.todolistfragments.databinding.ItemTaskLayoutBinding
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.utils.TaskDiffUtil

class TaskAdapter(
    private var items: MutableList<Task>
) :
    RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    var listener: Listener? = null

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
                listener?.onClick(item)
            }

            cvTaskItem.setOnLongClickListener {
                listener?.onLongClick(item)
                return@setOnLongClickListener true
            }

            ivMore.setOnClickListener {
                listener?.onMoreClick(it, item)
            }
        }
    } // bind data to UI above

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
//        this.items = items.toMutableList()
//        notifyDataSetChanged()
        val taskDiffUtil = TaskDiffUtil(this.items, items)
        val diffResult = DiffUtil.calculateDiff(taskDiffUtil)

        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    class ItemTaskHolder(val binding: ItemTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(task: Task)
        fun onLongClick(task: Task)
        fun onMoreClick(view: View, task: Task)
    }
}