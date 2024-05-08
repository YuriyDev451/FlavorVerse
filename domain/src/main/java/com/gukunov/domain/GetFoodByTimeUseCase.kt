package com.gukunov.domain

import com.gukunov.data.GetFoodByPriceRepositoryInterface
import com.gukunov.data.GetFoodByTimeRepositoryInterface
import com.gukunov.entity.food.Food
import javax.inject.Inject

class GetFoodByTimeUseCase@Inject constructor(private val foodRepository: GetFoodByTimeRepositoryInterface) {
    suspend operator fun invoke(time: Int): List<Food> {
        return foodRepository.getFoodsByTime(time)
    }
}