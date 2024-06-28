package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.example.madcamp01.DB.Entities.Image

@Dao
interface ImageDao {
    @Insert
    suspend fun insertImage(image: Image)

    @Query("SELECT * FROM Image WHERE imageId = :imageId")
    suspend fun getImageById(imageId: Int): Image?

    @Update
    suspend fun updateImage(image: Image)

    @Delete
    suspend fun deleteImage(image: Image)
}