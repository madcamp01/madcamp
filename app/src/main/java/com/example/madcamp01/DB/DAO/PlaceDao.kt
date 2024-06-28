package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.yourapp.database.entities.Place

@Dao
interface PlaceDao {
    @Insert
    suspend fun insertPlace(place: Place)

    @Query("SELECT * FROM Place WHERE placeId = :placeId")
    suspend fun getPlaceById(placeId: Int): Place?

    @Update
    suspend fun updatePlace(place: Place)

    @Delete
    suspend fun deletePlace(place: Place)
}