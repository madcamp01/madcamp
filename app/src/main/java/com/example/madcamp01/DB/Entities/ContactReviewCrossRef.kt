package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "reviewId"])
data class ContactReviewCrossRef(
    val personId: Int,
    val reviewId: Int
)
