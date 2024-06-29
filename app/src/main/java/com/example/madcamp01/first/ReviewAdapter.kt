package com.example.madcamp01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.Entities.Review

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
        private val reviewRating: TextView = itemView.findViewById(R.id.review_rating)

        fun bind(review: Review) {
            reviewText.text = review.comment
            reviewDate.text = review.date
            reviewRating.text = review.rating.toString()
        }
    }
}
