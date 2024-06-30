package com.example.madcamp01.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madcamp01.DB.Entities.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM Image")
    suspend fun getAllImages(): List<Image>

    @Insert
    suspend fun insertImage(image: Image)

    @Query("SELECT * FROM Image WHERE imageSrc = :uri")
    suspend fun getImageByUri(uri: String): Image?
}
