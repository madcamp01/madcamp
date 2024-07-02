package com.example.madcamp01.second

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.R
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        val database = AppDatabase.getInstance(requireContext())

        lifecycleScope.launch {
            val imagesWithReviews = getImagesWithReviewsFromDb(database)
            if (imagesWithReviews.isEmpty()) {
                insertDummyData(database)
            }
            loadImages()
        }


        return view
    }

    private suspend fun insertDummyData(database: AppDatabase) {
        val dummyImages = listOf(
            Image(1, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img1)),
            Image(2, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img2)),
            Image(3, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img3)),
            Image(4, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img4)),
            Image(5, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img5)),
            Image(6, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img6)),
            Image(7, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img7)),
            Image(8, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img8)),
            Image(9, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img9)),
            Image(10, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img10)),
            Image(11, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img11)),
            Image(12, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img12)),
            Image(13, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img13)),
            Image(14, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img14)),
            Image(15, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img15)),
            Image(16, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img16)),
            Image(17, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img17)),
            Image(18, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img18)),
            Image(19, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img19)),
            Image(20, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img20))
        )
        withContext(Dispatchers.IO) {
            database.imageDao().apply {
                dummyImages.forEach { insertImage(it) }
            }
        }
    }

    private fun loadImages() {
        val database = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            val imagesWithReviews = getImagesWithReviewsFromDb(database)
            imageAdapter = ImageAdapter(imagesWithReviews) { imageUri, imageId, review ->
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_URI", imageUri.toString())
                    putExtra("IMAGE_ID", imageId)
                    putExtra("REVIEW", review)
                }
                startActivity(intent)
            }
            recyclerView.adapter = imageAdapter
        }
    }

    private suspend fun getImagesWithReviewsFromDb(database: AppDatabase): List<Pair<Image, Review?>> {
        return withContext(Dispatchers.IO) {
            val images = database.imageDao().getAllImages()
            val reviews = database.reviewDao().getAllReviews()
            images.map { image ->
                val review = reviews.find { it.imageId == image.imageId }
                image to review
            }
        }
    }
}
