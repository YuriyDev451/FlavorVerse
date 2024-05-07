package com.gukunov.domain.di

import com.gukunov.data.CategoryRepositoryInterface
import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.data.GetByCategoryFoodRepositoryInterface
import com.gukunov.data.SearchRepositoryInterface
import com.gukunov.domain.GetCategoryUseCase
import com.gukunov.domain.GetFoodUseCase
import com.gukunov.domain.GetFoodsByCategoryUseCase
import com.gukunov.domain.SearchFoodUseCase
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


    @Provides
    @Singleton
    fun provideSearchUseCase(repositoryInterface: SearchRepositoryInterface) = SearchFoodUseCase(repositoryInterface)


    @Provides
    @Singleton
    fun provideGetFoodByCategoryUseCase(repositoryInterface: GetByCategoryFoodRepositoryInterface) = GetFoodsByCategoryUseCase(repositoryInterface)



}