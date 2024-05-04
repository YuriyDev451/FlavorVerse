package com.gukunov.entity.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val Id: Int,
    val loc: String
) : Parcelable
