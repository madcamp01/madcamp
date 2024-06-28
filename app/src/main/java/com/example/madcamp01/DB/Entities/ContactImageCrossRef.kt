package com.example.madcamp01.DB.Entities

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "imageId"])
data class ContactImageCrossRef(
    val personId: Int,
    val imageId: Int
)
