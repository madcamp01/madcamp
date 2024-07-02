package com.example.madcamp01

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
            try {
                contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            Log.d("AddReviewActivity", "Selected image URI: $selectedImageUri")
        }
    }
    private val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success) {
            imageButton.setImageURI(selectedImageUri)
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
            showImagePickerDialog()
        }

        reviewAddButton.setOnClickListener {
            saveReview()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("사진 찍기", "갤러리에서 선택")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("이미지 선택")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> checkCameraPermissionAndTakePhoto()
                1 -> checkStoragePermissionAndPickImage()
            }
        }
        builder.show()
    }

    private fun checkCameraPermissionAndTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    private fun checkStoragePermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImage.launch("image/*")
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImage.launch("image/*")
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
            }
        }
    }

    private fun takePhoto() {
        val photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())!!
        selectedImageUri = photoUri
        takePhoto.launch(photoUri)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()
                } else {
                    Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                    showPermissionSettingsDialog()
                }
            }
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage.launch("image/*")
                } else {
                    Toast.makeText(this, "스토리지 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                    showPermissionSettingsDialog()
                }
            }
        }
    }

    private fun showPermissionSettingsDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("권한 설정")
        builder.setMessage("필요한 권한이 허용되지 않았습니다. 설정에서 권한을 허용해주세요.")
        builder.setPositiveButton("설정으로 가기") { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }
        builder.setNegativeButton("취소", null)
        builder.show()
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
            Log.d("AddReviewActivity","imageId")

            val database = AppDatabase.getInstance(applicationContext)
            var placeId = getPlaceId(database, placeName)
            if (placeId == null) {
                placeId = insertPlace(database, placeName)
            }
            val imageUri =  selectedImageUri.let { getRealPathFromURI(parent, it!!) }?.let{Uri.parse(it)}?:selectedImageUri
            var imageId = getImageId(database, imageUri.toString())

            if (imageId == null) {
                Log.d("AddReviewActivity", "Inserting new image URI: ${imageUri}")
                imageId = insertImage(database, imageUri.toString())
            } else {
                Log.d("AddReviewActivity", "Found existing image ID: $imageId for URI: ${imageUri}")
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
            Log.d("AddReviewActivity","$review.imageId")

            database.reviewDao().insertReview(review)
            Log.d("AddReviewActivity","$review.imageId")
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
        val lat = 33.0 + Random.nextDouble(5.0) // 위도: 33.0 ~ 38.0
        val lon = 125.0 + Random.nextDouble(8.0) // 경도: 125.0 ~ 132.0
        val place = Place(0, placeName, lat, lon)
        return withContext(Dispatchers.IO) {
            database.placeDao().insertPlace(place).toInt()
        }
    }

    private suspend fun getImageId(database: AppDatabase, imageUri: String): Int? {
        return withContext(Dispatchers.IO) {
            database.imageDao().getImageByUri(imageUri)?.imageId
        }
    }

    private suspend fun insertImage(database: AppDatabase, imageUri: String): Int {
        val image = Image(0, imageUri)
        return withContext(Dispatchers.IO) {
            database.imageDao().insertImage(image).toInt()
        }
    }

    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.providers.media.documents" == uri.authority) {
                val documentId = DocumentsContract.getDocumentId(uri)
                val id = documentId.split(":")[1]
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toLong()
                )
                return getDataColumn(context, contentUri, null, null)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        }
        return null
    }

    fun getDataColumn(context: Context, uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        context.contentResolver.query(uri, arrayOf(MediaStore.Images.Media.DATA), selection, selectionArgs, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return cursor.getString(columnIndex)
            }
        }
        return null
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
        private const val STORAGE_REQUEST_CODE = 101
    }
}
