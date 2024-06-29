package com.example.madcamp01

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
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

    private var contactId: Int = -1
    private lateinit var contact: Contact
    private var isEditMode: Boolean = false

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

        contactId = intent.getIntExtra("CONTACT_ID", -1)

        loadContact()

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
            val database = AppDatabase.getDatabase(applicationContext)
            contact = database.contactDao().getContactById(contactId)!!

            withContext(Dispatchers.Main) {
                imageView.setImageURI(contact.profilePicture)
                textName.text = contact.personName
                textPhoneNumber.text = contact.contactInfo
                textStatus.text = contact.memo
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

    private fun saveContact() {
        val updatedContact = contact.copy(
            personName = editTextName.text.toString(),
            contactInfo = editTextPhoneNumber.text.toString(),
            memo = editTextStatus.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getDatabase(applicationContext)
            database.contactDao().updateContact(updatedContact)

            withContext(Dispatchers.Main) {
                contact = updatedContact
                toggleEditMode(false)
                loadContact()
            }
        }
    }

    private fun deleteContact() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getDatabase(applicationContext)
            database.contactDao().deleteContact(contact)

            withContext(Dispatchers.Main) {
                setResult(RESULT_OK) // 삭제 후 결과 설정
                finish()
            }
        }
    }
}
