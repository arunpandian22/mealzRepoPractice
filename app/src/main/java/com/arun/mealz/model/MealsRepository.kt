package com.arun.mealz.model

import com.arun.mealz.model.api.MealsApiService
import com.arun.mealz.model.api.NetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class MealsRepository(private val networkBuilder: NetworkBuilder = NetworkBuilder()) {
    val tag = "MealsRepository"

    var mealsApiService: MealsApiService = networkBuilder.getMealWebService()

    fun getMeals(successCallback: (MealsCategoryResponse) -> Unit) {
        mealsApiService.getMeals().enqueue(object : Callback<MealsCategoryResponse> {
            override fun onResponse(
                call: Call<MealsCategoryResponse>,
                response: Response<MealsCategoryResponse>
            ) {
                if (response.isSuccessful)
                    response.body()
                        ?.let { mealsCategoryResponse -> successCallback(mealsCategoryResponse) }
            }

            override fun onFailure(call: Call<MealsCategoryResponse>, t: Throwable) {

            }

        })
    }
}