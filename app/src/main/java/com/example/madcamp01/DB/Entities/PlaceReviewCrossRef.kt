package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["placeId", "reviewId"])
data class PlaceReviewCrossRef(
    val placeId: Int,
    val reviewId: Int
)
