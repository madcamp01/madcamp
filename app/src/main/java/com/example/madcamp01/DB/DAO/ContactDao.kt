package com.example.madcamp01.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madcamp01.DB.Entities.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE personId = :personId")
    suspend fun getContactById(personId: Int): Contact?

    @Query("SELECT * FROM Contact")
    suspend fun getAllContacts(): List<Contact>

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}
