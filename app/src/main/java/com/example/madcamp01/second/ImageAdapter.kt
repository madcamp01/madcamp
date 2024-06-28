package com.example.madcamp01.second

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.databinding.ItemImageBinding

class ImageAdapter(private val imageList: List<Uri>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

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
            Log.d("ImageAdapter", "Binding image: $imageUri")

            binding.imageView.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ImageFullscreenActivity::class.java).apply {
                    putParcelableArrayListExtra("imageList", ArrayList(imageList))
                    putExtra("initialPosition", position)
                }
                context.startActivity(intent)
            }
        }
    }
}
