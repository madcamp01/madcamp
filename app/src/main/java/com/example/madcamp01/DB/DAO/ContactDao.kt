package com.example.madcamp01.DB.DAO

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madcamp01.DB.Entities.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE personId = :personId")
    fun getContactByIdLive(personId: Int): LiveData<Contact?>

    @Query("SELECT * FROM Contact")
    fun getAllContactsLive():LiveData<List<Contact>>

    @Query("SELECT * FROM Contact WHERE profilePicture = :imageUri")
    suspend fun getContactByProfilePicture(imageUri: Uri): Contact?

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}