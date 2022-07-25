package com.arun.mealz.ui.meals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arun.mealz.model.MealsRepository
import com.arun.mealz.model.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsCategoriesViewModel(private val mealsRepository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    val tag = "MealsCategoriesViewModel"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealState.value = meals
        }
    }

    val mealState =
        mutableStateOf(emptyList<MealsResponse>())

    suspend fun getMeals(): List<MealsResponse> {
        return mealsRepository.getMeals().categories
    }

}