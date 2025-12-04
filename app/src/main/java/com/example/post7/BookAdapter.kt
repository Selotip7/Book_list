package com.example.post7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.post7.databinding.ItemBookBinding

class BookAdapter(
    private var list: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBookBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.txtTitle.text = book.title
            binding.txtRelease.text = book.releaseDate

            // Load cover image menggunakan Glide
            Glide.with(binding.root.context)
                .load(book.cover)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imgCover)

            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}