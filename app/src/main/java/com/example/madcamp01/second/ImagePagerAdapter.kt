// ImagePagerAdapter.kt
package com.example.madcamp01.second

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.R

class ImagePagerAdapter(
    private val images: List<Image>,
    private val imageClickListener: (Int) -> Unit // 클릭 이벤트 처리를 위한 리스너
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            // 각 이미지 뷰에 클릭 리스너를 설정합니다.
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    imageClickListener(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.imageView.setImageURI(image.imageSrc)
    }

    override fun getItemCount(): Int = images.size
}
