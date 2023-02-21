package com.joel.todolistfragments.utils

import androidx.recyclerview.widget.DiffUtil
import com.joel.todolistfragments.data.model.Task

class TaskDiffUtil(private val oldList: List<Task>, private val newList: List<Task>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}