package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.example.madcamp01.DB.Entities.Place

@Dao
interface PlaceDao {
    @Insert
    suspend fun insertPlace(place: Place):Long

    @Query("SELECT * FROM Place WHERE placeId = :placeId")
    suspend fun getPlaceById(placeId: Int): Place?

    @Query("SELECT * FROM Place WHERE placeName = :placeName")
    suspend fun getPlaceByName(placeName: String):Place?

    @Update
    suspend fun updatePlace(place: Place)

    @Delete
    suspend fun deletePlace(place: Place)
}