package com.joel.todolistfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joel.todolistfragments.databinding.ItemLayoutImageBinding
import com.joel.todolistfragments.data.model.Image
import com.joel.todolistfragments.utils.update

class ImageSliderAdapter(private var items: MutableList<Image>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemLayoutImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivImage.setImageResource(item.id)
    }

    override fun getItemCount() = items.size

    fun setImages(items: List<Image>) {
        val oldItems = this.items
        this.items.clear()
        this.items.addAll(items)
        update(oldItems, items) { task1, task2 ->
            task1.id == task2.id
        }
    }

    class ImageViewHolder(val binding: ItemLayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}