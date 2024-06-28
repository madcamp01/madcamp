package com.example.madcamp01.first

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.R
import com.example.madcamp01.db.contacts.ContactDAO


class AddressTab : AppCompatActivity() {
    private lateinit var contactDAO: ContactDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_tab)

        contactDAO = ContactDAO(this)

        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val job = intent.getStringExtra("status")
        val number = intent.getStringExtra("phoneNumber")
        val imageResId = intent.getIntExtra("imageResId", 0)

        val textName: TextView = findViewById(R.id.textname)
        val textPhoneNumber: TextView = findViewById(R.id.textPhoneNumber)
        val textStatus: TextView = findViewById(R.id.textStatus)
        val imageView: ImageView = findViewById(R.id.imageView)
        val buttonEdit: Button = findViewById(R.id.buttonEdit)
        val buttonDelete:Button = findViewById(R.id.deleteButton)

        // Setting initial values
        textName.text = name
        textPhoneNumber.text = number
        textStatus.text = job
        imageView.setImageResource(imageResId)

        // Creating EditTexts programmatically
        val editTextName = EditText(this).apply {
            setText(name)
            visibility = View.GONE
        }
        val editTextPhoneNumber = EditText(this).apply {
            setText(number)
            visibility = View.GONE
        }
        val editTextStatus = EditText(this).apply {
            setText(job)
            visibility = View.GONE
        }

        buttonDelete.setOnClickListener(){
            contactDAO.deleteContact(intent.getIntExtra("id",-1))
            finish()
        }
        // A
        // dding EditTexts to the parent layout
        val parentLayout: LinearLayout = findViewById(R.id.phone_number_frame)
        parentLayout.addView(editTextName, 0)
        parentLayout.addView(editTextPhoneNumber, 1)
        parentLayout.addView(editTextStatus, 2)

        // Edit button functionality
        buttonEdit.setOnClickListener {
            if (textName.visibility == View.VISIBLE) {
                textName.visibility = View.GONE
                editTextName.visibility = View.VISIBLE
                textPhoneNumber.visibility = View.GONE
                editTextPhoneNumber.visibility = View.VISIBLE
                textStatus.visibility = View.GONE
                editTextStatus.visibility = View.VISIBLE
                buttonDelete.visibility = View.VISIBLE

                buttonEdit.text = "저장"
            } else {
                val newName = editTextName.text.toString()
                val newPhoneNumber = editTextPhoneNumber.text.toString()
                val newStatus = editTextStatus.text.toString()

                contactDAO.updateDatabase(id, newName, newPhoneNumber, newStatus)

                textName.text = editTextName.text.toString()
                textPhoneNumber.text = editTextPhoneNumber.text.toString()
                textStatus.text = editTextStatus.text.toString()
                textName.visibility = View.VISIBLE
                editTextName.visibility = View.GONE
                textPhoneNumber.visibility = View.VISIBLE
                editTextPhoneNumber.visibility = View.GONE
                textStatus.visibility = View.VISIBLE
                editTextStatus.visibility = View.GONE
                buttonDelete.visibility = View.GONE
                buttonEdit.text = "편집"
            }
        }
    }

}