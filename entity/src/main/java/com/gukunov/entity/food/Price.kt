package com.gukunov.entity.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    val Id: Int,
    val Value: String
) : Parcelable
