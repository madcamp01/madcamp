package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true) val placeId: Int,  /*장소 구분*/
    val placeName: String,      /*장소 이름*/
    val latitude: Double,
    val longitude: Double
)
