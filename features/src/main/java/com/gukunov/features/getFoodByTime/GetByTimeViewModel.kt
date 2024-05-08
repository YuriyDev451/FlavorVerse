package com.gukunov.features.getFoodByTime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetFoodByPriceUseCase
import com.gukunov.domain.GetFoodByTimeUseCase
import com.gukunov.entity.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetByTimeViewModel @Inject constructor(
    private val getFoodsByTimeUseCase: GetFoodByTimeUseCase
): ViewModel() {

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> get() = _foods

    fun loadFoodsByCategory(priceId: Int) {
        viewModelScope.launch {
            _foods.value = getFoodsByTimeUseCase.invoke(priceId)
        }
    }
}