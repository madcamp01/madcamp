package com.example.madcamp01.third

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val reviewComment: TextView = itemView.findViewById(R.id.commentTextView)
    private val reviewRating: RatingBar = itemView.findViewById(R.id.reviewRate)
    private val reviewDate: TextView = itemView.findViewById(R.id.dateTextView)
    private val reviewImage: ImageView = itemView.findViewById(R.id.reviewPictureImageView)

    fun bind(review: Review) {
        reviewComment.text = review.comment
        reviewRating.rating = review.rating.toFloat()
        reviewDate.text = review.date
        GlobalScope.launch(Dispatchers.Main) {
            val imageUri = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(itemView.context).imageDao().getImageById(review.imageId)!!.imageSrc
            }
            reviewImage.setImageURI(Uri.parse(imageUri))
        }
    }
}
