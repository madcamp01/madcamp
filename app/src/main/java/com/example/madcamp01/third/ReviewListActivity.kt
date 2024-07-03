package com.example.madcamp01.third

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.R
import com.example.madcamp01.ReviewAdapter

class ReviewListActivity : Activity() { // 변경: AppCompatActivity에서 Activity로 변경
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var placeNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_list)

        placeNameTextView = findViewById(R.id.placeNameTextView)
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView)
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)

        val reviews = intent.getSerializableExtra("REVIEWS") as? List<Review> ?: emptyList()
        val placeName = intent.getStringExtra("PLACE_NAME")

        placeNameTextView.text = placeName
        reviewAdapter = ReviewAdapter(reviews)
        reviewRecyclerView.adapter = reviewAdapter
    }
}
