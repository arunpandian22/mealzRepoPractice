package com.arun.mealz.model

import kotlinx.serialization.Serializable


/**
 * Created by Arun Pandian  on 23/07/22.
 */
@Serializable
data class MealsCategoryResponse(val categories: List<MealsResponse>)