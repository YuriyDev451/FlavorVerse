package com.gukunov.features.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetFoodUseCase
import com.gukunov.entity.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(private val getFoodUseCase: GetFoodUseCase):ViewModel() {
//
//
//
//    private val _foods = MutableLiveData<List<Food>>()
//    val foods: LiveData<List<Food>> = _foods
//
//
//     fun getFoods() {
//        viewModelScope.launch {
//            getFoodUseCase.execute().collect { foods ->
//                _foods.value = foods
//            }
//        }
//    }
//
//}