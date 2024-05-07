package com.gukunov.data

import com.google.firebase.database.DatabaseReference
import com.gukunov.entity.food.Food
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface GetByCategoryFoodRepositoryInterface {
    suspend fun getFoodsByCategory(categoryId: Int): List<Food>
}

class GetFoodByCategoryRepository@Inject constructor(private val firebaseDatabase: DatabaseReference) : GetByCategoryFoodRepositoryInterface {

    override suspend fun getFoodsByCategory(categoryId: Int): List<Food> {
        val foods = mutableListOf<Food>()
        firebaseDatabase.child("Foods").get().await().children.forEach { dataSnapshot ->
            dataSnapshot.getValue(Food::class.java)?.let { food ->
                if (food.CategoryId == categoryId) {
                    foods.add(food)
                }
            }
        }
        return foods
    }

}