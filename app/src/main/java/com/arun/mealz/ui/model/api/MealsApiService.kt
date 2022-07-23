package com.arun.mealz.ui.model.api

import com.arun.mealz.ui.model.MealsCategoryResponse
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Arun Pandian  on 23/07/22.
 */
interface MealsApiService {
    @GET("v1/1/categories.php")
    fun getMeals(): Call<MealsCategoryResponse>
}



