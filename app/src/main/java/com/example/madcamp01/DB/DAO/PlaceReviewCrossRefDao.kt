package com.example.madcamp01.DB.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.madcamp01.DB.Entities.PlaceReviewCrossRef

@Dao
interface PlaceReviewCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaceReviewCrossRef(crossRef: PlaceReviewCrossRef)

    @Delete
    suspend fun deletePlaceReviewCrossRef(crossRef: PlaceReviewCrossRef)

    @Query("SELECT * FROM PlaceReviewCrossRef where placeId = :placeId AND reviewId = :reviewId")
    suspend fun getPlaceReviewCrossRef(placeId : Int, reviewId : Int) : PlaceReviewCrossRef?

    @Query("SELECT * FROM PlaceReviewCrossRef where reviewId = :reviewId")
    suspend fun getReviewsForPlace(reviewId : Int) : List<PlaceReviewCrossRef>
}