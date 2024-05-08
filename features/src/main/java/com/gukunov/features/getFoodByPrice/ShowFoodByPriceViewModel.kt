package com.gukunov.features.getFoodByPrice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gukunov.domain.GetFoodByPriceUseCase
import com.gukunov.domain.GetFoodsByCategoryUseCase
import com.gukunov.entity.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowFoodByPriceViewModel@Inject constructor(
    private val getFoodsByPriceUseCase: GetFoodByPriceUseCase
): ViewModel() {

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> get() = _foods

    fun loadFoodsByCategory(priceId: Int) {
        viewModelScope.launch {
            _foods.value = getFoodsByPriceUseCase.invoke(priceId)
        }
    }
}