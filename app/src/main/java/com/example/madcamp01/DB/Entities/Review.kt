package com.example.madcamp01.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) val reviewId: Int, /*reviewID*/
    val placeId: Int,       /*placeId*/
    val personId: Int,      /*personId*/
    val imageId: Int,       /*imageId*/
    val rating: Int,        /*personId 가 남긴 평점*/
    val comment: String,    /*personId 가 남긴 코멘트*/
    val date: String        /*리뷰를 남긴 날짜*/
)
