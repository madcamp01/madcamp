package com.example.madcamp01.first

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
        profileViewButton = view.findViewById(R.id.profileButton)
        profileViewButton.setOnClickListener({
            val intent = Intent(context, ProfileActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE)
        })
        loadContacts()

        return view
    }
    //수정

    private fun loadContacts() {
        val database = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            val contacts = getContactsFromDb(database).filter{it.personId!=1}.sortedBy { it.personName }
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
                contactAdapter.updateContacts(contacts)
                recyclerView.adapter = contactAdapter
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val profileUri = data?.getStringExtra("PROFILE_URI")
            profileUri?.let {
                profileViewButton.setImageURI(Uri.parse(it))
            }
            loadContacts() // 삭제 후 연락처 목록 다시 로드n
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
