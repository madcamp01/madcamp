package com.example.madcamp01.second

import android.app.Application
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madcamp01.DB.Entities.Image
import kotlin.concurrent.thread

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    init {
        loadImagesFromGallery(application)
    }

    private fun loadImagesFromGallery(context: Context) {
        thread {
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaStore.Images.Media._ID)
            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC LIMIT 21"
            val cursor = context.contentResolver.query(uri, projection, null, null, sortOrder)

            val images = mutableListOf<Image>()
            cursor?.use {
                val columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(columnIndexId)
                    val contentUri = Uri.withAppendedPath(uri, id.toString())
                    images.add(Image(id.toInt(), contentUri))
                }
            }
            _imageList.postValue(images)
        }
    }
}
