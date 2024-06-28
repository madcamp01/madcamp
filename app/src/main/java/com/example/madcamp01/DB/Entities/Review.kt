package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) val reviewId: Int,
    val placeId: Int,
    val personId: Int,
    val imageId: Int,
    val rating: Int,
    val comment: String,
    val date: String
)
