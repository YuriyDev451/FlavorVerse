package com.gukunov.data

import com.google.firebase.database.DatabaseReference
import com.gukunov.entity.food.Food
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface GetFoodByPriceRepositoryInterface{
    suspend fun getFoodsByPrice(prc: Int): List<Food>
}

class GetFoodByPriceRepository@Inject constructor(private val firebaseDatabase: DatabaseReference):GetFoodByPriceRepositoryInterface {
    override suspend fun getFoodsByPrice(prc: Int): List<Food> {
        val foods = mutableListOf<Food>()
        firebaseDatabase.child("Foods").get().await().children.forEach { dataSnapshot ->
            dataSnapshot.getValue(Food::class.java)?.let { food ->
                if (food.PriceId == prc) {
                    foods.add(food)
                }
            }
        }
        return foods
    }
}