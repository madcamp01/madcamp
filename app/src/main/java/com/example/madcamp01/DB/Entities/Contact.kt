package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val personId: Int,
    val personName: String,
    val contactInfo: String,
    val memo: String,
    val profilePicture: String
)
