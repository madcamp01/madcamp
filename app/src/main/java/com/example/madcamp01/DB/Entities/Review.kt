package com.example.madcamp01.DB.Entities

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(reviewId)
        parcel.writeInt(placeId)
        parcel.writeInt(personId)
        parcel.writeInt(imageId)
        parcel.writeInt(rating)
        parcel.writeString(comment)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}
