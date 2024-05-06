package com.gukunov.entity.food

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("Value") var Value: String? = null
) : Parcelable
