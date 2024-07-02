package com.example.madcamp01

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.second.FullScreenImageActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviews.size

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val reviewText: TextView = itemView.findViewById(R.id.review_text)
        private val reviewDate: TextView = itemView.findViewById(R.id.review_date)
        private val reviewRating: RatingBar = itemView.findViewById(R.id.reviewRate)
        private val reviewLayout: LinearLayout = itemView.findViewById(R.id.ReviewLayout)
        private val reviewPictureImageView: ImageView =itemView.findViewById(R.id.reviewPictureImageView)

        fun bind(review: Review) {
            reviewText.text = review.comment
            reviewDate.text = review.date
            reviewRating.rating = review.rating.toFloat()
            GlobalScope.launch(Dispatchers.Main) {
                val imageUri = withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(itemView.context).imageDao().getImageById(review.imageId)!!.imageSrc
                }
                reviewPictureImageView.setImageURI(imageUri)
            }

            reviewLayout.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_ID", review.imageId)
                }
                context.startActivity(intent)
            }
        }
    }

}
