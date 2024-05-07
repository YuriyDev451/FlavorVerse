package com.gukunov.domain

import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.data.SearchRepositoryInterface
import com.gukunov.entity.food.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchFoodUseCase@Inject constructor(private val repositoryInterface: SearchRepositoryInterface) {
    fun execute(query: String): Flow<List<Food>> {
        return repositoryInterface.searchFoods(query)
    }
}