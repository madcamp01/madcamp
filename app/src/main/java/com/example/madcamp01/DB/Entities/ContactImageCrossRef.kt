package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "imageId"])
data class ContactImageCrossRef(/*image 에 좋아요를 남긴 사람*/
    val personId: Int,
    val imageId: Int
)
