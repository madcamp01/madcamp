package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.example.madcamp01.DB.Entities.Review

@Dao
interface ReviewDao {
    @Insert
    suspend fun insertReview(review: Review)

    @Insert
    suspend fun insertAll(reviews: List<Review>)

    @Query("SELECT * FROM Review WHERE reviewId = :reviewId")
    suspend fun getReviewById(reviewId: Int): Review?

    @Query("SELECT * FROM Review WHERE imageId = :imageId")
    suspend fun getReviewsByImageId(imageId: Int): List<Review>

    @Query("SELECT * FROM Review WHERE personId = :contactId")
    suspend fun getReviewsByContactId(contactId: Int): List<Review>

    @Query("SELECT * FROM Review WHERE imageId = :imageId")
    suspend fun getReviewsForImage(imageId: Int): List<Review>

    @Update
    suspend fun updateReview(review: Review)

    @Delete
    suspend fun deleteReview(review: Review)
}