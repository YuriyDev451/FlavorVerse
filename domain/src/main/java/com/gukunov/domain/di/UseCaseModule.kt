package com.gukunov.domain.di

import com.gukunov.data.CategoryRepositoryInterface
import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.domain.GetCategoryUseCase
import com.gukunov.domain.GetFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFoodUseCase(repositoryInterface: FoodRepositoryInterface) = GetFoodUseCase(repositoryInterface)

    @Provides
    @Singleton
    fun provideCategoryUseCase(repositoryInterface: CategoryRepositoryInterface) = GetCategoryUseCase(repositoryInterface)
}