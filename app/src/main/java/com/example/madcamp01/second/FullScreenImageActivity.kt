package com.example.madcamp01.second

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Repository.DataRepository
import com.example.madcamp01.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var fullScreenImageView: ImageView
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        fullScreenImageView = findViewById(R.id.fullScreenImageView)
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView)
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)

        val imageId: Int = intent.getIntExtra("IMAGE_ID", -1)
        val reviewId: Int = intent.getIntExtra("REVIEW_ID", -1)
        val imageUri: String? = intent.getStringExtra("IMAGE_URI")

        if (imageUri != null) {
            fullScreenImageView.setImageURI(Uri.parse(imageUri))
        }

        if (reviewId != -1) {
            loadReview(reviewId)
        }

        supportActionBar?.hide()

        fullScreenImageView.setOnClickListener {
            finish()
        }
    }

    private fun loadReview(reviewId: Int) {
        val database = AppDatabase.getInstance(this)
        lifecycleScope.launch {
            val review = withContext(Dispatchers.IO) { database.reviewDao().getReviewById(reviewId) }
            review?.let {
                reviewAdapter = ReviewAdapter(it)
                reviewRecyclerView.adapter = reviewAdapter
            }
        }
    }
}

