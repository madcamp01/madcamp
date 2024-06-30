package com.example.madcamp01.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.DB.DAO.ContactDao
import com.example.madcamp01.DB.DAO.ReviewDao

@Database(entities = [Contact::class, Image::class, Review::class], version = 2) // 버전 증가
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun imageDao(): ImageDao
    abstract fun reviewDao(): ReviewDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // 스키마가 변경되었을 때 데이터베이스를 다시 생성
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
