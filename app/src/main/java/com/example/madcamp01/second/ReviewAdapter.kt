package com.example.madcamp01.second

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.ContactDetailActivity
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.DAO.ContactDao
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewAdapter(private val review: Review) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ratingBar: RatingBar = itemView.findViewById(R.id.reviewRate)
        val commentTextView: TextView = itemView.findViewById(R.id.commentTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val profilePictureImageView: ImageView = itemView.findViewById(R.id.profilePictureImageView)

        fun bind(contactDao: ContactDao, review: Review) {
            GlobalScope.launch(Dispatchers.Main) {
                val contact = withContext(Dispatchers.IO) {
                    contactDao.getContactByPersonId(review.personId)
                }
                profilePictureImageView.setImageURI(contact.profilePicture)
                ratingBar.rating = review.rating.toFloat()
                commentTextView.text = review.comment
                dateTextView.text = review.date

                profilePictureImageView.setOnClickListener {
                    val context = itemView.context
                    val intent = Intent(context, ContactDetailActivity::class.java).apply {
                        putExtra("CONTACT_ID", contact.personId)
                        putExtra("CONTACT_NAME",contact.personName)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val database = AppDatabase.getInstance(holder.itemView.context)
        holder.bind(database.contactDao(), review)
    }

    override fun getItemCount(): Int = 1
}
