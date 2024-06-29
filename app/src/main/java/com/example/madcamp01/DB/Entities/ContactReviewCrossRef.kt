package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "reviewId"])
data class ContactReviewCrossRef(/*personId가 쓴 리뷰들*/
    val personId: Int,
    val reviewId: Int
)
