package com.example.madcamp01

import android.Manifest
import android.app.Activity
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
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.first.ReviewAdapterProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textName: TextView
    private lateinit var editTextName: EditText
    private lateinit var textPhoneNumber: TextView
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var textStatus: TextView
    private lateinit var editTextStatus: EditText
    private lateinit var buttonEdit: Button
    private lateinit var deleteButton: Button
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var reviewEditButton: Button
    private lateinit var deleteSelectedReviewsButton: Button
    private lateinit var cancelEditReviewsButton: Button
    private lateinit var imageOverlay: View
    private lateinit var imageAddIcon: ImageView

    private var contactId: Int = -1
    private lateinit var contact: Contact
    private var isEditMode: Boolean = false
    private var isReviewEditMode: Boolean = false
    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            Log.d("permission", "pickImage:$selectedImageUri")

            imageView.setImageURI(it)

            try {
                contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                Log.d("permission", "fuckedUP")
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    private val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success && selectedImageUri != null) {
            imageView.setImageURI(selectedImageUri)
        }
    }

    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            takePhotoIfPermissionGranted()
        } else {
            Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            showPermissionSettingsDialog()
        }
    }

    private val requestStoragePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            pickImage.launch("image/*")
        } else {
            Toast.makeText(this, "스토리지 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            showPermissionSettingsDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imageView = findViewById(R.id.imageView)
        textName = findViewById(R.id.textname)
        editTextName = findViewById(R.id.editTextName)
        textPhoneNumber = findViewById(R.id.textPhoneNumber)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        textStatus = findViewById(R.id.textStatus)
        editTextStatus = findViewById(R.id.editTextStatus)
        buttonEdit = findViewById(R.id.buttonEdit)
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView)
        reviewEditButton = findViewById(R.id.reviewEditButton)
        deleteSelectedReviewsButton = findViewById(R.id.deleteSelectedReviewsButton)
        cancelEditReviewsButton = findViewById(R.id.cancelEditReviewsButton)
        imageOverlay = findViewById(R.id.imageOverlay)
        imageAddIcon = findViewById(R.id.imageAddIcon)

        reviewRecyclerView.layoutManager = LinearLayoutManager(this)

        contactId = 1

        loadContact()
        loadReviews()

        imageView.setOnClickListener {
            if (isEditMode) {
                showImagePickerDialog()
            }
        }

        buttonEdit.setOnClickListener {
            if (isEditMode) {
                saveContact()
            } else {
                toggleEditMode(true)
            }
        }

        reviewEditButton.setOnClickListener {
            toggleReviewEditMode(true)
        }

        deleteSelectedReviewsButton.setOnClickListener {
            deleteSelectedReviews()
        }

        cancelEditReviewsButton.setOnClickListener {
            toggleReviewEditMode(false)
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
            takePhotoIfPermissionGranted()
        } else {
            requestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun checkStoragePermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImage.launch("image/*")
            } else {
                requestStoragePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImage.launch("image/*")
            } else {
                requestStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun takePhotoIfPermissionGranted() {
        val photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        if (photoUri != null) {
            selectedImageUri = photoUri
            takePhoto.launch(photoUri)
        } else {
            Toast.makeText(this, "사진 촬영을 위한 URI를 생성할 수 없습니다.", Toast.LENGTH_SHORT).show()
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

    private fun loadContact() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            val fetchedContact = database.contactDao().getContactById(contactId)

            withContext(Dispatchers.Main) {
                if (fetchedContact != null) {
                    contact = fetchedContact
                    imageView.setImageURI(contact.profilePicture)
                    textName.text = contact.personName
                    textPhoneNumber.text = contact.contactInfo
                    textStatus.text = contact.memo
                } else {
                    Toast.makeText(applicationContext, "Contact not found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun toggleEditMode(enabled: Boolean) {
        isEditMode = enabled
        if (enabled) {
            buttonEdit.text = "저장"
            textName.visibility = View.GONE
            editTextName.visibility = View.VISIBLE
            editTextName.setText(contact.personName)

            textPhoneNumber.visibility = View.GONE
            editTextPhoneNumber.visibility = View.VISIBLE
            editTextPhoneNumber.setText(contact.contactInfo)

            textStatus.visibility = View.GONE
            editTextStatus.visibility = View.VISIBLE
            editTextStatus.setText(contact.memo)

            imageOverlay.visibility = View.VISIBLE
            imageAddIcon.visibility = View.VISIBLE
        } else {
            buttonEdit.text = "편집"
            textName.visibility = View.VISIBLE
            editTextName.visibility = View.GONE

            textPhoneNumber.visibility = View.VISIBLE
            editTextPhoneNumber.visibility = View.GONE

            textStatus.visibility = View.VISIBLE
            editTextStatus.visibility = View.GONE

            imageOverlay.visibility = View.GONE
            imageAddIcon.visibility = View.GONE
        }
    }

    private fun saveContact() {
        val updatedContact = contact.copy(
            personName = editTextName.text.toString(),
            contactInfo = editTextPhoneNumber.text.toString(),
            memo = editTextStatus.text.toString(),
            profilePicture = (selectedImageUri?.let { getRealPathFromURI(this, it) }?.let { Uri.parse(it) } ?: selectedImageUri)!!
        )

        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            database.contactDao().updateContact(updatedContact)
            Log.d("profile", "picture: ${updatedContact.profilePicture}")
            withContext(Dispatchers.Main) {
                contact = updatedContact
                toggleEditMode(false)
                loadContact()
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra("PROFILE_URI", contact.profilePicture.toString())
                })
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_OK, Intent().apply {
            putExtra("PROFILE_URI", contact.profilePicture.toString())
        })
        super.onBackPressed()
    }

    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        Log.d("getRealPathFromURI", "URI: $uri")

        // Document URI
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.providers.media.documents" == uri.authority) {
                val documentId = DocumentsContract.getDocumentId(uri)
                val id = documentId.split(":")[1]
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toLong()
                )
                return getDataColumn(context, contentUri, null, null)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val documentId = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), documentId.toLong()
                )
                return getDataColumn(context, contentUri, null, null)
            }
        }
        // Content URI
        else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        }
        // File URI
        else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
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


    private fun loadReviews() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            val reviews = database.reviewDao().getReviewsByContactId(contactId).sortedByDescending { it.date }

            withContext(Dispatchers.Main) {
                reviewRecyclerView.adapter = ReviewAdapterProfile(reviews, ::deleteReview, isReviewEditMode)
            }
        }
    }

    private fun deleteReview(review: Review) {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            database.reviewDao().deleteReview(review)

            withContext(Dispatchers.Main) {
                loadReviews() // 리뷰 삭제 후 다시 로드
            }
        }
    }

    private fun toggleReviewEditMode(enabled: Boolean) {
        isReviewEditMode = enabled
        if (enabled) {
            reviewEditButton.visibility = View.GONE
            deleteSelectedReviewsButton.visibility = View.VISIBLE
            cancelEditReviewsButton.visibility = View.VISIBLE
        } else {
            reviewEditButton.visibility = View.VISIBLE
            deleteSelectedReviewsButton.visibility = View.GONE
            cancelEditReviewsButton.visibility = View.GONE
        }
        loadReviews() // 편집 모드 상태로 리뷰 로드
    }

    private fun deleteSelectedReviews() {
        val adapter = reviewRecyclerView.adapter as? ReviewAdapterProfile
        val selectedReviews = adapter?.getSelectedReviews() ?: emptyList()
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            selectedReviews.forEach { review ->
                database.reviewDao().deleteReview(review)
            }

            withContext(Dispatchers.Main) {
                toggleReviewEditMode(false)
                loadReviews() // 선택된 리뷰 삭제 후 다시 로드
            }
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
        private const val STORAGE_REQUEST_CODE = 101
    }
}
