package com.example.madcamp01

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Place
import com.example.madcamp01.DB.Entities.Review
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class AddReviewActivity : AppCompatActivity() {

    private lateinit var editTextComment: EditText
    private lateinit var editTextPlace: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var imageButton: ImageButton
    private lateinit var reviewAddButton: Button
    private lateinit var cancelButton: Button

    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            imageButton.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        editTextComment = findViewById(R.id.editTextMemo)
        editTextPlace = findViewById(R.id.editTextPlace)
        ratingBar = findViewById(R.id.ratingBar)
        imageButton = findViewById(R.id.imageButton)
        reviewAddButton = findViewById(R.id.reviewAddButton)
        cancelButton = findViewById(R.id.cancelButton)

        imageButton.setOnClickListener {
            pickImage.launch("image/*")
        }

        reviewAddButton.setOnClickListener {
            saveReview()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveReview() {
        val comment = editTextComment.text.toString()
        val rating = ratingBar.rating.toInt()
        val placeName = editTextPlace.text.toString()
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        if (comment.isBlank() || placeName.isBlank() || selectedImageUri == null) {
            Toast.makeText(this, "모든 필드를 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            var placeId = getPlaceId(database, placeName)
            if (placeId == null) {
                placeId = insertPlace(database, placeName)
            }

            var imageId = getImageId(database, selectedImageUri!!)
            if (imageId == null) {
                imageId = insertImage(database, selectedImageUri!!)
            }

            val review = Review(
                reviewId = 0,
                placeId = placeId,
                personId = 1, // Default personId
                imageId = imageId,
                rating = rating,
                comment = comment,
                date = currentDate
            )
            database.reviewDao().insertReview(review)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@AddReviewActivity, "리뷰가 추가되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private suspend fun getPlaceId(database: AppDatabase, placeName: String): Int? {
        return withContext(Dispatchers.IO) {
            database.placeDao().getPlaceByName(placeName)?.placeId
        }
    }

    private suspend fun insertPlace(database: AppDatabase, placeName: String): Int {
        val placeLocation = generateRandomLocationInKorea()
        val place = Place(0, placeName, placeLocation)
        return withContext(Dispatchers.IO) {
            database.placeDao().insertPlace(place).toInt()
        }
    }

    private fun generateRandomLocationInKorea(): String {
        val lat = 33.0 + Random.nextDouble(5.0) // 위도: 33.0 ~ 38.0
        val lon = 125.0 + Random.nextDouble(8.0) // 경도: 125.0 ~ 132.0
        return "$lat,$lon"
    }

    private suspend fun getImageId(database: AppDatabase, imageUri: Uri): Int? {
        return withContext(Dispatchers.IO) {
            database.imageDao().getImageByUri(imageUri.toString())?.imageId
        }
    }

    private suspend fun insertImage(database: AppDatabase, imageUri: Uri): Int {
        val image = Image(0, imageUri)
        return withContext(Dispatchers.IO) {
            database.imageDao().insertImage(image).toInt()
        }
    }
}
