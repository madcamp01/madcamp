package com.example.madcamp01.DB.DAO

import androidx.room.*
import com.example.madcamp01.DB.Entities.Review

@Dao
interface ReviewDao {
    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM Review WHERE reviewId = :reviewId")
    suspend fun getReviewById(reviewId: Int): Review?

    @Update
    suspend fun updateReview(review: Review)

    @Delete
    suspend fun deleteReview(review: Review)
}