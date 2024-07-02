package com.example.madcamp01.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.R
import com.example.madcamp01.DB.Entities.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        selectedReviews.clear()
        notifyDataSetChanged()

    }

    fun getSelectedReviews(): List<Review> {
        return selectedReviews.toList()
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val reviewComment: TextView = itemView.findViewById(R.id.commentTextView)
        private val reviewRating: RatingBar = itemView.findViewById(R.id.reviewRate)
        private val reviewDate: TextView = itemView.findViewById(R.id.dateTextView)
        val selectCheckBox: CheckBox = itemView.findViewById(R.id.select_checkbox)
        private val reviewImage: ImageView = itemView.findViewById(R.id.reviewPictureImageView)

        fun bind(review: Review) {
            reviewComment.text = review.comment
            reviewRating.rating = review.rating.toFloat()
            reviewDate.text = review.date
            GlobalScope.launch(Dispatchers.Main) {
                val imageUri = withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(itemView.context).imageDao().getImageById(review.imageId)!!.imageSrc
                }
                reviewImage.setImageURI(imageUri)
            }
        }
    }
}
