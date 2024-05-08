package com.gukunov.domain

import com.gukunov.data.CategoryRepositoryInterface
import com.gukunov.data.GetPriceRepositoryInterface
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Price
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPriceUseCase@Inject constructor(private val repositoryInterface: GetPriceRepositoryInterface) {

    fun execute(): Flow<List<Price>> {
        return repositoryInterface.getPrice()
    }

}