package com.nathalie.todolistfragments.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.databinding.ItemLayoutNoteBinding

class NoteAdapter(
    private var items: List<Note>,
    val onClick: (item: Note) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.ItemNoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNoteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutNoteBinding.inflate(layoutInflater, parent, false)
        return ItemNoteHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemNoteHolder, position: Int) {
        val item = items[position]

        holder.binding.run {
            if (
                item.color == "#5f5449" || item.color == "#9b6a6c" || item.color == "#b09398"
            ) {
                tvTitle.setTextColor(Color.parseColor("#ffffff"))
                tvDetails.setTextColor(Color.parseColor("#ffffff"))
            }

            tvTitle.text = item.title
            tvDetails.text = item.details
            cvNoteItem.setCardBackgroundColor(Color.parseColor(item.color))

            cvNoteItem.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount() = items.size

    fun setNotes(items: List<Note>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemNoteHolder(val binding: ItemLayoutNoteBinding) :
        RecyclerView.ViewHolder(binding.root)
}