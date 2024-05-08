package com.gukunov.entity.food

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    @SerializedName("Id") val Id: Int? = null,
    @SerializedName("Value") val Value: String? = null
) : Parcelable
