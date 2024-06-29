package com.example.madcamp01.second

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.databinding.ItemImageBinding

class ImageAdapter(
    private val imageList: List<Uri>,
    private val onItemClick: (Uri, Int) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position], position)
    }

    override fun getItemCount(): Int = imageList.size

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: Uri, position: Int) {
            binding.imageView.setImageURI(imageUri)

            binding.imageView.setOnClickListener {
                onItemClick(imageUri, position)
            }
        }
    }
}

