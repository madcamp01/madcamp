package com.example.madcamp01.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madcamp01.DB.DAO.*
import com.example.madcamp01.DB.Entities.*

@Database(
    entities = [
        Place::class,
        Review::class,
        Contact::class,
        Image::class,
        ContactReviewCrossRef::class,
        PlaceReviewCrossRef::class,
        ContactImageCrossRef::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
    abstract fun reviewDao(): ReviewDao
    abstract fun contactDao(): ContactDao
    abstract fun imageDao(): ImageDao
    abstract fun contactImageCrossRefDao(): ContactImageCrossRefDao
    abstract fun placeReviewCrossRefDao(): PlaceReviewCrossRefDao
}