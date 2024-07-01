package com.example.madcamp01.first

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp01.AddReviewActivity
import com.example.madcamp01.ContactDetailActivity
import com.example.madcamp01.R
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.ProfileActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private val REQUEST_CODE = 1
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val addReviewButton: Button = view.findViewById(R.id.addReviewButton)
        addReviewButton.setOnClickListener {
            val intent = Intent(context, AddReviewActivity::class.java)
            startActivity(intent)
        }
        val profileViewButton:Button = view.findViewById(R.id.profileButton)
        profileViewButton.setOnClickListener({
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        })
        loadContacts()

        return view
    }
    //수정

    private fun loadContacts() {
        val database = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            val contacts = getContactsFromDb(database)
            if (contacts.isEmpty()) {
                insertDummyData(database)
                loadContacts() // Dummy data 삽입 후 다시 로드
            } else {
                contactAdapter = ContactAdapter(contacts) { contact ->
                    val intent = Intent(context, ContactDetailActivity::class.java).apply {
                        putExtra("CONTACT_ID", contact.personId)
                    }
                    startActivityForResult(intent, REQUEST_CODE)
                }
                contactAdapter.updateContacts(contacts)
                recyclerView.adapter = contactAdapter
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadContacts() // 삭제 후 연락처 목록 다시 로드
        }
    }

    private suspend fun insertDummyData(database: AppDatabase) {
        val dummyContacts = listOf(
            Contact(1, "John Doe", "010-1234-5678", "Friend from work", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img1)),
            Contact(2, "Jane Smith", "010-2345-6789", "Gym buddy", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img2)),
            Contact(3, "Mike Johnson", "010-3456-7890", "College friend", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img3)),
            Contact(4, "Emily Davis", "010-4567-8901", "Neighbor", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img4)),
            Contact(5, "Sarah Brown", "010-5678-9012", "Book club member", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img5)),
            Contact(6, "Chris Wilson", "010-6789-0123", "Cousin", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img6)),
            Contact(7, "Jessica Lee", "010-7890-1234", "High school friend", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img7)),
            Contact(8, "David Kim", "010-8901-2345", "Team member", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img8)),
            Contact(9, "Laura Martinez", "010-9012-3456", "Sister", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img9)),
            Contact(10, "James Anderson", "010-0123-4567", "Brother", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img10))
        )
        withContext(Dispatchers.IO) {
            database.contactDao().apply {
                dummyContacts.forEach { insertContact(it) }
            }
        }
    }

    private suspend fun getContactsFromDb(database: AppDatabase): List<Contact> {
        return withContext(Dispatchers.IO) {
            database.contactDao().getAllContacts()
        }
    }
}
