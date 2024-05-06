package com.gukunov.entity.food

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("ImagePath") var ImagePath: String? = null,
    @SerializedName("Name") var Name: String? = null
) : Parcelable
