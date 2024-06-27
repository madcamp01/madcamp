package com.example.madcamp01.first

data class Contact(
    val name: String,
    val status: String,
    val imageResId: Int
)

fun getSortedContacts(contacts: List<Contact>): List<Contact> {
    return contacts.sortedBy { it.name }
}