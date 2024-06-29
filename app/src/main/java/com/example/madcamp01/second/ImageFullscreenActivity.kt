package com.example.madcamp01.second

import ImageDetailViewModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Repository.DataRepository
import com.example.madcamp01.databinding.ActivityImageFullscreenBinding

class ImageFullscreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageFullscreenBinding
    private lateinit var repository: DataRepository
    private val imageDetailViewModel: ImageDetailViewModel by viewModels {
        ImageDetailViewModelFactory(repository)
    }

    companion object {
        private const val EXTRA_IMAGE_LIST = "imageList"
        private const val EXTRA_INITIAL_POSITION = "initialPosition"

        fun newIntent(context: Context, imageList: List<Uri>, initialPosition: Int): Intent {
            val intent = Intent(context, ImageFullscreenActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_IMAGE_LIST, ArrayList(imageList))
            intent.putExtra(EXTRA_INITIAL_POSITION, initialPosition)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityImageFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize repository here
        val database = AppDatabase.getInstance(this)
        repository = DataRepository(
            database.contactDao(),
            database.imageDao(),
            database.reviewDao()
        )

        val imageList = intent.getParcelableArrayListExtra<Uri>(EXTRA_IMAGE_LIST) ?: emptyList()
        val initialPosition = intent.getIntExtra(EXTRA_INITIAL_POSITION, 0)

        val adapter = FullscreenImageAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(initialPosition, false)

        imageDetailViewModel.image.observe(this, { image ->
            image?.let {
                imageDetailViewModel.loadReviews(it.imageId)
            }
        })

        imageDetailViewModel.reviews.observe(this, { reviews ->
            reviews?.let {
                binding.textViewReviews.text = reviews.joinToString("\n") { review ->
                    "Rating: ${review.rating}, Comment: ${review.comment}, Date: ${review.date}"
                }
            }
        })
    }
}
