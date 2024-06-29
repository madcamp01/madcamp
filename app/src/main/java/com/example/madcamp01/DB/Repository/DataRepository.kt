package com.example.madcamp01.DB.Repository

import android.net.Uri
import com.example.madcamp01.DB.DAO.ContactDao
import com.example.madcamp01.DB.DAO.ImageDao
import com.example.madcamp01.DB.DAO.ReviewDao
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review

class DataRepository(
    private val contactDao: ContactDao,
    private val imageDao: ImageDao,
    private val reviewDao: ReviewDao // 여기에 인스턴스 변수로 선언
) {

    suspend fun getContactByProfilePicture(imageUri: Uri): Contact? {
        return contactDao.getContactByProfilePicture(imageUri)
    }

    suspend fun getImageByUri(imageUri: Uri): Image? {
        return imageDao.getImageByUri(imageUri.toString())
    }

    suspend fun getReviewsByImageId(imageId: Int): List<Review> {
        return reviewDao.getReviewsByImageId(imageId) // 소문자로 시작하는 인스턴스 변수로 접근
    }
}
