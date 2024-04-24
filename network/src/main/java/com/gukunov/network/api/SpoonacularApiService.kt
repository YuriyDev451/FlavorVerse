package com.gukunov.network.api

import retrofit2.http.GET

interface SpoonacularApiService {

    @GET("")
    suspend fun getRandomRecipeList()
}