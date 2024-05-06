package com.gukunov.domain

import com.gukunov.data.CategoryRepositoryInterface
import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase@Inject constructor(private val repositoryInterface: CategoryRepositoryInterface) {

    fun execute(): Flow<List<Category>> {
        return repositoryInterface.getCategory()
    }

}