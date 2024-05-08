package com.gukunov.data

import com.google.firebase.database.DatabaseReference
import com.gukunov.entity.food.Food
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface GetFoodByTimeRepositoryInterface{
    suspend fun getFoodsByTime(prc: Int): List<Food>
}
class GetFoodByTimeRepository@Inject constructor(private val firebaseDatabase: DatabaseReference):GetFoodByTimeRepositoryInterface {
    override suspend fun getFoodsByTime(time: Int): List<Food> {
        val foods = mutableListOf<Food>()
        firebaseDatabase.child("Foods").get().await().children.forEach { dataSnapshot ->
            dataSnapshot.getValue(Food::class.java)?.let { food ->
                if (food.PriceId == time) {
                    foods.add(food)
                }
            }
        }
        return foods
    }
}
