package com.example.madcamp01.second

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Review
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

        val imageUri: Uri? = intent.getParcelableExtra("IMAGE_URI")

        if (imageUri != null) {
            fullScreenImageView.setImageURI(imageUri)

            val database = AppDatabase.getInstance(this)
            val repository = DataRepository(database.contactDao(), database.imageDao(), database.reviewDao())
            lifecycleScope.launch {
                val image = repository.getImageByUri(imageUri)
                if (image != null) {
                    val reviews = withContext(Dispatchers.IO) { repository.getReviewsByImageId(image.imageId) }
                    reviewAdapter = ReviewAdapter(reviews)
                    reviewRecyclerView.adapter = reviewAdapter
                }
            }
        }

        supportActionBar?.hide()

        fullScreenImageView.setOnClickListener {
            finish()
        }
    }
}
