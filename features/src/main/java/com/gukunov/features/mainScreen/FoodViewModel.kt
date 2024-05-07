package com.gukunov.features.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetCategoryUseCase
import com.gukunov.domain.GetFoodUseCase
import com.gukunov.domain.SearchFoodUseCase
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel@Inject constructor(
    private val getFoodUseCase: GetFoodUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
    ): ViewModel() {


    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _category = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>> = _category

//    private val _searchResult = MutableLiveData<List<Food>>()
//    val searchResult: LiveData<List<Food>> get() = _searchResult


    init {
        viewModelScope.launch {
            getFoods()
        }
        viewModelScope.launch {
            getCategory()
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

//    fun searchFood(query: String) {
//        viewModelScope.launch {
//            searchFoodUseCase.execute(query).collect {
//                _searchResult.value = it
//            }
//        }
//    }
}