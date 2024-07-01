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

        val imageId: Int = intent.getIntExtra("IMAGE_ID", -1)

        if (imageId != -1) {
            val database = AppDatabase.getInstance(this)
            val repository =
                DataRepository(database.contactDao(), database.imageDao(), database.reviewDao())
            lifecycleScope.launch {
                val image = withContext(Dispatchers.IO) { repository.getImageById(imageId) }
                if (image != null) {
                    // 이미지를 가져왔으니 해당 이미지의 리뷰들을 가져옴
                    val reviews =
                        withContext(Dispatchers.IO) { repository.getReviewsByImageId(imageId) }
                    reviewAdapter = ReviewAdapter(reviews)
                    reviewRecyclerView.adapter = reviewAdapter

                    // 이미지를 표시
                    val imageUri = Uri.parse(image.imageSrc.toString()) // image 객체에서 URI 추출
                    fullScreenImageView.setImageURI(imageUri)
                }
            }
        }

        supportActionBar?.hide()

        fullScreenImageView.setOnClickListener {
            finish()
        }
    }
}