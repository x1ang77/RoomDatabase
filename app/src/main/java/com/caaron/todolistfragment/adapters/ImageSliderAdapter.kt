package com.caaron.todolistfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caaron.todolistfragment.databinding.ItemLayoutImageBinding
import com.caaron.todolistfragment.data.model.Image

class ImageSliderAdapter(var items: List<Image>): RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemLayoutImageBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
       val item = items[position]
        holder.binding.ivImage.setImageResource(item.id)
    }

    override fun getItemCount() = items.size

    class ImageViewHolder(val binding: ItemLayoutImageBinding):RecyclerView.ViewHolder(binding.root)

}