package com.arun.mealz.ui.meals

import android.util.Log
import androidx.lifecycle.ViewModel
import com.arun.mealz.ui.model.MealsRepository


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsCategoriesViewModel(private val mealsRepository: MealsRepository = MealsRepository()) :
    ViewModel() {
    val tag = "MealsCategoriesViewModel"

    fun getMeals() {
         mealsRepository.getMeals() {
            Log.d(tag, "response: ${it.categories.size}")
        }
    }
}