package com.gukunov.domain

import com.gukunov.data.GetByCategoryFoodRepositoryInterface
import com.gukunov.data.GetFoodByCategoryRepository
import com.gukunov.entity.food.Food
import javax.inject.Inject

class GetFoodsByCategoryUseCase @Inject constructor(private val foodRepository: GetByCategoryFoodRepositoryInterface) {
    suspend operator fun invoke(categoryId: Int): List<Food> {
        return foodRepository.getFoodsByCategory(categoryId)
    }
}