package com.arun.mealz.ui.meals

import androidx.lifecycle.ViewModel
import com.arun.mealz.model.MealsCategoryResponse
import com.arun.mealz.model.MealsRepository


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsCategoriesViewModel(private val mealsRepository: MealsRepository = MealsRepository()) :
    ViewModel() {
    val tag = "MealsCategoriesViewModel"

    fun getMeals(successMealResponse: (MealsCategoryResponse) -> Unit) {
         mealsRepository.getMeals(successMealResponse)
    }
}