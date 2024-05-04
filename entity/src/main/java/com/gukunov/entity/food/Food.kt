package com.gukunov.entity.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val BestFood: Boolean,
    val CategoryId: Int,
    val Description: String,
    val Id: Int,
    val ImagePath: String,
    val LocationId: Int,
    val Price: Double,
    val PriceId: Int,
    val Star: Double,
    val TimeId: Int,
    val TimeValue: Int,
    val Title: String
) : Parcelable
