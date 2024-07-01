package com.example.madcamp01.second

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.R

class ImageAdapter(
    private val images: List<Image>,
    private val itemClickListener: (Uri, Int) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.imageView.setImageURI(image.imageSrc)
        holder.imageView.setOnClickListener {
            itemClickListener(image.imageSrc, image.imageId)
        }
    }

    override fun getItemCount(): Int = images.size
}
