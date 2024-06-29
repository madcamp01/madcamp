package com.example.madcamp01.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcamp01.DB.DAO.ContactDao
import com.example.madcamp01.DB.Entities.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val contactDao: ContactDao) : ViewModel() {
//    val allContacts: List<Contact> = contactDao.getAllContacts()

//    suspend fun getContactById(personId: Int): Contact? {
//        return contactDao.getContactById(personId)
//    }

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.insertContact(contact)
        }
    }


    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.deleteContact(contact)
        }
    }
}