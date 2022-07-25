package com.arun.mealz.ui.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.arun.mealz.model.MealsRepository
import com.arun.mealz.model.MealsResponse


/**
 * Created by Arun Pandian  on 25/07/22.
 */
class MealsDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var mealsState = mutableStateOf<MealsResponse?>(null)
    val tag = "MealsDetailViewModel"
    private val mealsRepository: MealsRepository = MealsRepository.getInstance()
    init {
        val mealId = savedStateHandle.get<String>("fId")?:""
        Log.d(tag,"mealId: $mealId ${ mealsRepository.getMeal(mealId)?.name}" )

        mealsState.value = mealsRepository.getMeal(mealId)
    }
}