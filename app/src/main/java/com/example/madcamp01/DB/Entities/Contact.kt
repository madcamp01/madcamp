package com.example.madcamp01.DB.Entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.madcamp01.DB.Converters
import com.example.madcamp01.DB.Entities.Review

@Entity
@TypeConverters(Converters::class)
data class Contact(
    @PrimaryKey(autoGenerate = true) val personId: Int,     /*아이디*/
    val personName: String,     /*이름 (본인 설정 / 내 설정)*/
    val contactInfo: String,    /*전화번호*/
    val memo: String,           /*프로필 설명 (내 설정)*/
    val profilePicture: Uri     /*프로필 사진*/
)
