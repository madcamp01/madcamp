package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true) val placeId: Int,
    val placeName: String,
    val placeLocation: String
)
