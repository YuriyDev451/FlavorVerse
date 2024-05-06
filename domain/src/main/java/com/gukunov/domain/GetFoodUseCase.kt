package com.gukunov.domain

import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.entity.food.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(private val repositoryInterface: FoodRepositoryInterface) {

    fun execute(): Flow<List<Food>> {
        return repositoryInterface.getFoods()
    }

}