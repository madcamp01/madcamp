package com.example.madcamp01.second

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.R
import com.example.madcamp01.DB.Entities.Review

class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val commentTextView: TextView = itemView.findViewById(R.id.commentTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.ratingTextView.text = "Rating: ${review.rating}"
        holder.commentTextView.text = review.comment
        holder.dateTextView.text = review.date
    }

    override fun getItemCount(): Int = reviews.size
}
