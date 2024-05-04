package com.gukunov.entity.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val Id: Int,
    val Image: String,
    val Name: String
) : Parcelable
