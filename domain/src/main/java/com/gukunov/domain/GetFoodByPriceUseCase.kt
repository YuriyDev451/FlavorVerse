package com.gukunov.domain

import com.gukunov.data.GetByCategoryFoodRepositoryInterface
import com.gukunov.data.GetFoodByPriceRepositoryInterface
import com.gukunov.entity.food.Food
import javax.inject.Inject

class GetFoodByPriceUseCase@Inject constructor(private val foodRepository: GetFoodByPriceRepositoryInterface) {
    suspend operator fun invoke(prc: Int): List<Food> {
        return foodRepository.getFoodsByPrice(prc)
    }
}