package com.gukunov.features.searchFood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetFoodsByCategoryUseCase
import com.gukunov.domain.SearchFoodUseCase
import com.gukunov.entity.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel@Inject constructor(
    private val searchFoodUseCase: SearchFoodUseCase
): ViewModel() {

    private val _searchResult = MutableLiveData<List<Food>>()
    val searchResult: LiveData<List<Food>> get() = _searchResult

    fun searchFood(query: String) {
        viewModelScope.launch {
            searchFoodUseCase.execute(query).collect {
                _searchResult.value = it
            }
        }
    }



//    private val _foods = MutableLiveData<List<Food>>()
//    val foods: LiveData<List<Food>> get() = _foods
//
//    fun loadFoodsByCategory(categoryId: Int) {
//        viewModelScope.launch {
//            _foods.value = getFoodsByCategoryUseCase.invoke(categoryId)
//        }
//    }

}