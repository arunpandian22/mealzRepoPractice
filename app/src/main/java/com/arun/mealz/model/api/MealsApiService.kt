package com.arun.mealz.model.api

import com.arun.mealz.model.MealsCategoryResponse
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Arun Pandian  on 23/07/22.
 */
interface MealsApiService {
    @GET("v1/1/categories.php")
    suspend fun getMeals(): MealsCategoryResponse
}



