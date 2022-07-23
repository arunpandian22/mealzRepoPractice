package com.arun.mealz.ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by Arun Pandian  on 23/07/22.
 */
@Serializable
data class MealsResponse(
    @SerialName("idCategory")
    val id: String,
    @SerialName("strCategory")
    val name: String,
    @SerialName("strCategoryThumb")
    val imageUrl: String,
    @SerialName("strCategoryDescription")
    val description: String,
)