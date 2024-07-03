package com.example.madcamp01.first

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var profileViewButton: ImageView
    private lateinit var searchEditText: EditText
    private lateinit var noDataTextView: TextView
    private lateinit var allContacts: List<Contact> // 모든 연락처를 저장하는 리스트

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        searchEditText = view.findViewById(R.id.searchEditText)
        noDataTextView = view.findViewById(R.id.noDataTextView)

        val addReviewButton: Button = view.findViewById(R.id.addReviewButton)
        addReviewButton.setOnClickListener {
            val intent = Intent(context, AddReviewActivity::class.java)
            startActivity(intent)
        }
        profileViewButton = view.findViewById(R.id.profileButton)
        profileViewButton.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterContacts(s.toString())
            }
        })

        loadContacts()

        return view
    }

    private fun loadContacts() {
        val database = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            allContacts = getContactsFromDb(database)
            val contacts = allContacts.filter { it.personId != 1 }.sortedBy { it.personName }
            val profileContact = getContactById(database, 1)

            if (profileContact != null) {
                profileViewButton.setImageURI(profileContact.profilePicture)
            }

            contactAdapter = ContactAdapter(contacts) { contact ->
                val intent = Intent(context, ContactDetailActivity::class.java).apply {
                    putExtra("CONTACT_ID", contact.personId)
                    putExtra("CONTACT_NAME", contact.personName)
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
            recyclerView.adapter = contactAdapter
        }
    }

    private fun filterContacts(query: String) {
        val filteredContacts = allContacts.filter {
            it.personName.startsWith(query, ignoreCase = true)
        }
        if (filteredContacts.isEmpty()) {
            recyclerView.visibility = View.GONE
            noDataTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noDataTextView.visibility = View.GONE
            contactAdapter.updateContacts(filteredContacts)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val profileUri = data?.getStringExtra("PROFILE_URI")
            profileUri?.let {
                profileViewButton.setImageURI(Uri.parse(it))
            }
            loadContacts() // 삭제 후 연락처 목록 다시 로드
        }
    }

    private suspend fun getContactsFromDb(database: AppDatabase): List<Contact> {
        return withContext(Dispatchers.IO) {
            database.contactDao().getAllContacts()
        }
    }

    private suspend fun getContactById(database: AppDatabase, contactId: Int): Contact? {
        return withContext(Dispatchers.IO) {
            database.contactDao().getContactById(contactId)
        }
    }
}
