package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["imageId", "reviewId"])
data class ImageReviewCrossRef(
    val imageId: Int,
    val reviewId: Int
)
