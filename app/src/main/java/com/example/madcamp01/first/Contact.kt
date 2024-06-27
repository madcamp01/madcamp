package com.example.madcamp01.first

data class Contact(
    val name: String,
    val status: String,
    val imageResId: Int,
    //val userId : Int
)

fun getGroupedContacts(contacts: List<Contact>): Map<Char, List<Contact>> {
    return contacts
        .sortedBy { it.name }
        .groupBy { it.name.first() }
}