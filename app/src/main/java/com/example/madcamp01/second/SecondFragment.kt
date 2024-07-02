import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.R
import com.example.madcamp01.second.FullScreenImageActivity
import com.example.madcamp01.second.ImageAdapter
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

        loadImages()

        return view
    }

    private fun loadImages() {
        val database = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            val imagesWithReviews = getImagesWithReviewsFromDb(database)
            imageAdapter = ImageAdapter(imagesWithReviews) { imageUri, imageId, reviewId ->
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_URI", imageUri.toString())
                    putExtra("IMAGE_ID", imageId)
                    putExtra("REVIEW_ID", reviewId)
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
