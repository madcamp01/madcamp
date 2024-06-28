package com.example.madcamp01.db.contacts

data class Contact(
    val id:Int=0,
    val name: String,
    val status: String,
    val phoneNumber:String,
    val imageResId: Int
)

fun getSortedContacts(contacts: List<Contact>): List<Contact> {
    return contacts.sortedBy { it.name }
}