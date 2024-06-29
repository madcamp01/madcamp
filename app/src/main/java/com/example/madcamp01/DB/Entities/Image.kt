package com.example.madcamp01.DB.Entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true) val imageId: Int,      /*이미지 구분 */
    val imageSrc: Uri       /*이미지 src*/
    )