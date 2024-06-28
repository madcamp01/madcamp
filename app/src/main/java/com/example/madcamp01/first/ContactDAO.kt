package com.example.madcamp01.first

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.madcamp01.first.Contact

class ContactDAO(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun insertContact(contact: Contact) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", contact.name)
            put("phoneNumber", contact.phoneNumber)
            put("status", contact.status)
            put("imageResId",contact.imageResId)
        }
        Log.d("ContactDAO", "Inserted contact: ${contact.name}")
        db.insert("Contacts", null, values)
        db.close()
    }
    fun getAllContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val db = dbHelper.readableDatabase
        val selectQuery = "SELECT * FROM Contacts"
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val contact = Contact(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber")),
                    status = cursor.getString(cursor.getColumnIndexOrThrow("status")),
                    imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"))
                )
                contacts.add(contact)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return contacts
    }
}