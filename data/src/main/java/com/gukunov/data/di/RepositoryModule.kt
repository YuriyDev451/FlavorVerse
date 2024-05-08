package com.gukunov.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.gukunov.data.CategoryRepositoryInterface
import com.gukunov.data.FoodRepositoryInterface
import com.gukunov.data.GetByCategoryFoodRepositoryInterface
import com.gukunov.data.GetCategoryRepository
import com.gukunov.data.GetFoodByCategoryRepository
import com.gukunov.data.GetFoodByPriceRepository
import com.gukunov.data.GetFoodByPriceRepositoryInterface
import com.gukunov.data.GetFoodByTimeRepository
import com.gukunov.data.GetFoodByTimeRepositoryInterface
import com.gukunov.data.GetFoodRepository
import com.gukunov.data.GetPriceRepository
import com.gukunov.data.GetPriceRepositoryInterface
import com.gukunov.data.GetTimeRepository
import com.gukunov.data.GetTimeRepositoryInterface
import com.gukunov.data.SearchRepository
import com.gukunov.data.SearchRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    @Binds
    @Singleton
    abstract fun providerSearchRepository(repository: SearchRepository): SearchRepositoryInterface

    @Binds
    @Singleton
    abstract fun providerGetByCategoryFoodRepository(repository: GetFoodByCategoryRepository): GetByCategoryFoodRepositoryInterface

    @Binds
    @Singleton
    abstract fun providerGetPriceRepository(repository: GetPriceRepository): GetPriceRepositoryInterface

    @Binds
    @Singleton
    abstract fun providerGetFoodByPriceRepository(repository: GetFoodByPriceRepository): GetFoodByPriceRepositoryInterface


    @Binds
    @Singleton
    abstract fun providerGetFoodByTimeRepository(repository: GetFoodByTimeRepository): GetFoodByTimeRepositoryInterface


    @Binds
    @Singleton
    abstract fun providerGetTimeRepository(repository: GetTimeRepository): GetTimeRepositoryInterface

}