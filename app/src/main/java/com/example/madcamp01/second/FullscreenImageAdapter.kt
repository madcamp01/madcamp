package com.example.madcamp01.second

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.databinding.ItemFullscreenImageBinding

class FullscreenImageAdapter(private val imageList: List<Uri>) : RecyclerView.Adapter<FullscreenImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val binding: ItemFullscreenImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: Uri) {
            binding.imageView.setImageURI(imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemFullscreenImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}
