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
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: Uri) {
            binding.imageView.setImageURI(imageUri)
            Log.d("ImageAdapter", "Binding image: $imageUri")

            // Set OnClickListener to open image in fullscreen
            binding.imageView.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ImageFullscreenActivity::class.java).apply {
                    putExtra("imageUri", imageUri)
                }
                context.startActivity(intent)
            }
        }
    }
}
