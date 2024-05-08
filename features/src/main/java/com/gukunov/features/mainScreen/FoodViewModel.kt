package com.gukunov.features.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetCategoryUseCase
import com.gukunov.domain.GetFoodUseCase
import com.gukunov.domain.GetPriceUseCase
import com.gukunov.domain.GetTimeUseCase
import com.gukunov.domain.SearchFoodUseCase
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import com.gukunov.entity.food.Price
import com.gukunov.entity.food.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel@Inject constructor(
    private val getFoodUseCase: GetFoodUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getPriceUseCase: GetPriceUseCase,
    private val getTimeUseCase: GetTimeUseCase
    ): ViewModel() {


    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _category = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>> = _category

//    private val _searchResult = MutableLiveData<List<Food>>()
//    val searchResult: LiveData<List<Food>> get() = _searchResult

    private val _price = MutableLiveData<List<Price>>()
    val price: LiveData<List<Price>> = _price

    private val _time = MutableLiveData<List<Time>>()
    val time: LiveData<List<Time>> = _time


    init {
        viewModelScope.launch {
            getFoods()
        }
        viewModelScope.launch {
            getCategory()
        }
        viewModelScope.launch {
            getPrice()
        }
        viewModelScope.launch {
            getTime()
        }
    }

    private suspend fun getFoods() {

            getFoodUseCase.execute().collect { foods ->
                _foods.value = foods
            }
    }

    private suspend fun getCategory() {
        getCategoryUseCase.execute().collect{ category ->
            _category.value = category
        }
    }

    private suspend fun getPrice() {
        getPriceUseCase.execute().collect{ category ->
            _price.value = category
        }
    }
    private suspend fun getTime() {
        getTimeUseCase.execute().collect{ category ->
            _time.value = category
        }
    }

//    fun searchFood(query: String) {
//        viewModelScope.launch {
//            searchFoodUseCase.execute(query).collect {
//                _searchResult.value = it
//            }
//        }
//    }
}