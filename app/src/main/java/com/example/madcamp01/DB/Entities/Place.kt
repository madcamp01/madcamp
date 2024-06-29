package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true) val placeId: Int,  /*장소 구분*/
    val placeName: String,      /*장소 이름*/
    val placeLocation: String   /*장소 위치 / "위도,경도" 형식*/
)
