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
            val images = getImagesFromDb(database)
            if (images.isEmpty()) {
                insertDummyData(database)
            }
            loadImages()
        }

        return view
    }

    private suspend fun insertDummyData(database: AppDatabase) {
        val dummyImages = listOf(
            Image(1, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(2, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(3, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(4, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(5, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(6, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(7, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(8, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(9, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
            Image(10, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image))
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
            val images = getImagesFromDb(database)
            imageAdapter = ImageAdapter(images) { imageUri, imageId ->
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_URI", imageUri)
                    putExtra("IMAGE_ID", imageId)
                }
                startActivity(intent)
            }
            recyclerView.adapter = imageAdapter
        }
    }

    private suspend fun getImagesFromDb(database: AppDatabase): List<Image> {
        return withContext(Dispatchers.IO) {
            database.imageDao().getAllImages()
        }
    }
}
