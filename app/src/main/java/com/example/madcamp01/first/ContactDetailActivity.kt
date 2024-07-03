package com.example.madcamp01

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Review
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactDetailActivity : AppCompatActivity() {

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
    private lateinit var reviewListText:TextView

    private var contactId: Int = -1
    private lateinit var contact: Contact
    private var isEditMode: Boolean = false
    private var contactName:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        imageView = findViewById(R.id.imageView)
        textName = findViewById(R.id.textname)
        editTextName = findViewById(R.id.editTextName)
        textPhoneNumber = findViewById(R.id.textPhoneNumber)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        textStatus = findViewById(R.id.textStatus)
        editTextStatus = findViewById(R.id.editTextStatus)
        buttonEdit = findViewById(R.id.buttonEdit)
        deleteButton = findViewById(R.id.deleteButton)
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView)
        reviewListText = findViewById(R.id.ReviewListText)

        reviewRecyclerView.layoutManager = LinearLayoutManager(this)

        contactId = intent.getIntExtra("CONTACT_ID", -1)
        contactName = intent.getStringExtra("CONTACT_NAME").toString()

        loadContact()
        loadReviews()
        buttonEdit.setOnClickListener {
            if (isEditMode) {
                saveContact()
            } else {
                toggleEditMode(true)
            }
        }

        deleteButton.setOnClickListener {
            deleteContact()
        }
    }

    private fun loadContact() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            contact = database.contactDao().getContactById(contactId)!!

            withContext(Dispatchers.Main) {
                imageView.setImageURI(contact.profilePicture)
                textName.text = contact.personName
                textPhoneNumber.text = contact.contactInfo
                textStatus.text = contact.memo
                reviewListText.text = "${contact.personName} 님의 리뷰 목록입니다."
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

            deleteButton.visibility = View.VISIBLE
        } else {
            buttonEdit.text = "편집"
            textName.visibility = View.VISIBLE
            editTextName.visibility = View.GONE

            textPhoneNumber.visibility = View.VISIBLE
            editTextPhoneNumber.visibility = View.GONE

            textStatus.visibility = View.VISIBLE
            editTextStatus.visibility = View.GONE

            deleteButton.visibility = View.GONE
        }
    }
    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
    }


    private fun saveContact() {
        val updatedContact = contact.copy(
            personName = editTextName.text.toString(),
            contactInfo = editTextPhoneNumber.text.toString(),
            memo = editTextStatus.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            database.contactDao().updateContact(updatedContact)

            withContext(Dispatchers.Main) {
                contact = updatedContact
                toggleEditMode(false)
                loadContact()
                reviewListText.text = "${updatedContact.personName} 님의 리뷰 목록입니다."
                setResult(RESULT_OK)
            }
        }
    }

    private fun deleteContact() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            database.contactDao().deleteContact(contact)

            withContext(Dispatchers.Main) {
                setResult(RESULT_OK) // 삭제 후 결과 설정
                finish()
            }
        }
    }
    private fun loadReviews() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(applicationContext)
            var reviews = database.reviewDao().getReviewsByContactId(contactId).sortedByDescending { it.date }

//            if(reviews.isEmpty()) {
//                insertDummyData(database)
//                reviews = database.reviewDao().getReviewsByContactId(contactId)
//                    .sortedByDescending { it.date }
//            }
            withContext(Dispatchers.Main) {
                // RecyclerView 설정 및 리뷰 데이터 표시 코드 추가
                reviewRecyclerView.adapter = ReviewAdapter(reviews)

            }
        }
    }
//    //tobedeleted
//    private suspend fun insertDummyData(database: AppDatabase) {
//        val dummyReviews = listOf(
//            Review(1, 1, 1, 1, 4, "Great place!", "2023-06-01"),
//            Review(2, 2, 2, 2, 5, "Amazing experience!", "2023-06-02"),
//            Review(3, 3, 3, 3, 3, "It was okay.", "2023-06-03"),
//            Review(4, 4, 4, 4, 4, "Nice and peaceful.", "2023-06-04"),
//            Review(5, 5, 5, 5, 5, "Loved it!", "2023-06-05"),
//            Review(6, 1, 6, 6, 4, "Good place to visit.", "2023-06-06"),
//            Review(7, 2, 7, 7, 5, "Wonderful!", "2023-06-07"),
//            Review(8, 3, 8, 8, 3, "Average.", "2023-06-08"),
//            Review(9, 4, 9, 9, 4, "Quite good.", "2023-06-09"),
//            Review(10, 5, 10, 10, 5, "Excellent!", "2023-06-10"),
//            Review(11, 1, 1, 11, 4, "Enjoyed it.", "2023-06-11"),
//            Review(12, 2, 2, 12, 5, "Fantastic!", "2023-06-12"),
//            Review(13, 3, 3, 13, 3, "Not bad.", "2023-06-13"),
//            Review(14, 4, 4, 14, 4, "Pleasant.", "2023-06-14"),
//            Review(15, 5, 5, 15, 5, "Amazing!", "2023-06-15"),
//            Review(16, 1, 6, 16, 4, "Worth a visit.", "2023-06-16"),
//            Review(17, 2, 7, 17, 5, "Highly recommend!", "2023-06-17"),
//            Review(18, 3, 8, 18, 3, "It's okay.", "2023-06-18"),
//            Review(19, 4, 9, 19, 4, "Good.", "2023-06-19"),
//            Review(20, 5, 10, 20, 5, "Nice", "2023-06-28")
//        )
//        withContext(Dispatchers.IO) {
//            database.reviewDao().apply {
//                dummyReviews.forEach { insertReview(it) }
//            }
//        }
//    }
}
