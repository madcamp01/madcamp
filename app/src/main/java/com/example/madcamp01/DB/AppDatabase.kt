package com.example.madcamp01.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}