package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.example.madcamp01.DB.Entities.ContactImageCrossRef
@Dao
interface ContactImageCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContactImageCrossRef(crossRef: ContactImageCrossRef)

    @Delete
    suspend fun deleteContactImageCrossRef(crossRef: ContactImageCrossRef)

    @Query("SELECT * FROM ContactImageCrossRef where personId = :personId AND imageId = :imageId")
    suspend fun getContactImageCrossRef(personId : Int, imageId : Int) : ContactImageCrossRef?

    @Query("SELECT * FROM ContactImageCrossRef where personId = :personId")
    suspend fun getImagesForContact(personId : Int) : List<ContactImageCrossRef>

    @Query("SELECT * FROM ContactImageCrossRef where imageId = :imageId")
    suspend fun getContactsForImage(imageId : Int) : List<ContactImageCrossRef>
}