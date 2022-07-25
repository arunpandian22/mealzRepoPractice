package com.arun.mealz.model

import com.arun.mealz.model.api.MealsApiService
import com.arun.mealz.model.api.NetworkBuilder


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsRepository(private val networkBuilder: NetworkBuilder = NetworkBuilder()) {
    val tag = "MealsRepository"

    var mealsApiService: MealsApiService = networkBuilder.getMealWebService()

    suspend fun getMeals(): MealsCategoryResponse {
       return mealsApiService.getMeals()
    }
}