// FullScreenImageActivity.kt
package com.example.madcamp01.second

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var profileImageView: ImageView
    private var images: List<Image> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        viewPager = findViewById(R.id.viewPager)
        profileImageView = findViewById(R.id.profileImageView)

        val imageId: Int = intent.getIntExtra("IMAGE_ID", -1)
        val reviewId: Int = intent.getIntExtra("REVIEW_ID", -1)
        val database = AppDatabase.getInstance(this)

        lifecycleScope.launch {
            images = withContext(Dispatchers.IO) { database.imageDao().getAllImages() }
            imagePagerAdapter = ImagePagerAdapter(images) { position ->
                // 이미지를 클릭했을 때 원하는 동작을 수행할 수 있습니다.
                // 예를 들어, 특정 이미지의 리뷰를 불러오거나 다른 처리를 할 수 있습니다.
                val image = images[position]
                // 클릭된 이미지와 관련된 처리를 여기에 추가합니다.
            }
            viewPager.adapter = imagePagerAdapter
            val initialPosition = images.indexOfFirst { it.imageId == imageId }
            viewPager.setCurrentItem(initialPosition, false)
        }

        if (reviewId != -1) {
            lifecycleScope.launch {
                val personId = withContext(Dispatchers.IO) { database.reviewDao().getPersonIdByReviewId(reviewId) }
                personId?.let {
                    val contact = withContext(Dispatchers.IO) { database.contactDao().getContactById(it) }
                    contact?.let { contact ->
                        val profileUri = Uri.parse(contact.profilePicture.toString())
                        profileImageView.setImageURI(profileUri)
                    }
                }
            }
        }

        supportActionBar?.hide()
    }
}
