package com.gukunov.domain

import com.gukunov.data.GetPriceRepositoryInterface
import com.gukunov.data.GetTimeRepositoryInterface
import com.gukunov.entity.food.Price
import com.gukunov.entity.food.Time
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimeUseCase @Inject constructor(private val repositoryInterface: GetTimeRepositoryInterface) {

    fun execute(): Flow<List<Time>> {
        return repositoryInterface.getTime()
    }

}