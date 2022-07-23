package com.arun.mealz.ui.model.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit


/**
 * Created by Arun Pandian  on 23/07/22.
 */
class NetworkBuilder {
    private var mealsApiService: MealsApiService
    val tag = "NetworkBuilder"

    @OptIn(ExperimentalSerializationApi::class)
    var kotlinXConverterFactory: Converter.Factory = getKotlinxSerialisationConverterFactory()

    @ExperimentalSerializationApi
    private fun getKotlinxSerialisationConverterFactory(): Converter.Factory {
        val contentType = "application/json; charset=utf-8".toMediaType()
        val json = Json {
            coerceInputValues = true; ignoreUnknownKeys = true; prettyPrint = true; encodeDefaults =
            true; explicitNulls = false
        }
        return json.asConverterFactory(contentType)
    }

    init {
        Log.d(tag, "init")
        val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/")
            .addConverterFactory(kotlinXConverterFactory).build()
        mealsApiService = retrofit.create(MealsApiService::class.java)
    }

    fun getMealWebService(): MealsApiService {
        Log.d(tag, "getMealWebService jk jk ad ${mealsApiService}")
        return mealsApiService
    }

}