package com.arun.mealz.model

import android.util.Log
import com.arun.mealz.model.api.MealsApiService
import com.arun.mealz.model.api.NetworkBuilder


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsRepository(private val networkBuilder: NetworkBuilder = NetworkBuilder()) {
    val tag = "MealsRepository"

    var mealsApiService: MealsApiService = networkBuilder.getMealWebService()
    private var cachedMealResponse = listOf<MealsResponse>()

    suspend fun getMeals(): MealsCategoryResponse {
        val response = mealsApiService.getMeals()
        cachedMealResponse = response.categories
        return response
    }

    fun getMeal(id: String): MealsResponse? {
        return cachedMealResponse.firstOrNull { mealsResponse -> mealsResponse.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            Log.d("repo: "," $instance")
            instance ?: MealsRepository().also { instance = it }
        }
    }
}