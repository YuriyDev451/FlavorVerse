package com.gukunov.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providerFoodRepository(repository: GetFoodRepository): FoodRepositoryInterface

    @Binds
    @Singleton
    abstract fun providerCategoryRepository(repository: GetCategoryRepository): CategoryRepositoryInterface

}