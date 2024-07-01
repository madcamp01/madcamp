package com.example.madcamp01.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.R
import com.example.madcamp01.DB.Entities.Review

class ReviewAdapterProfile(
    private var reviews: List<Review>,
    private val onDeleteClick: (Review) -> Unit,
    private val isEditMode: Boolean
) : RecyclerView.Adapter<ReviewAdapterProfile.ReviewViewHolder>() {

    private val selectedReviews = mutableSetOf<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
        if (isEditMode) {
            holder.selectCheckBox.visibility = View.VISIBLE
            holder.selectCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedReviews.add(review)
                } else {
                    selectedReviews.remove(review)
                }
            }
        } else {
            holder.selectCheckBox.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = reviews.size

    fun updateReviews(newReviews: List<Review>) {
        reviews = newReviews
        notifyDataSetChanged()
    }

    fun getSelectedReviews(): List<Review> {
        return selectedReviews.toList()
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val reviewComment: TextView = itemView.findViewById(R.id.commentTextView)
        private val reviewRating: TextView = itemView.findViewById(R.id.ratingTextView)
        private val reviewDate: TextView = itemView.findViewById(R.id.dateTextView)
        val selectCheckBox: CheckBox = itemView.findViewById(R.id.select_checkbox)

        fun bind(review: Review) {
            reviewComment.text = review.comment
            reviewRating.text = review.rating.toString()
            reviewDate.text = review.date
        }
    }
}
