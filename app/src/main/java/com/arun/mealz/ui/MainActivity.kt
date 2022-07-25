package com.arun.mealz.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arun.mealz.ui.detail.MealDetailScreen
import com.arun.mealz.ui.detail.MealsDetailViewModel
import com.arun.mealz.ui.meals.MealsCategoriesScreen
import com.arun.mealz.ui.theme.MealzTheme


class MainActivity : ComponentActivity() {
    val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzTheme {
                // A surface container using the 'background' color from the theme
                MealsApp()
            }
        }
    }
}

@Composable
fun MealsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "meals_list") {
        composable(route = "meals_list") {
            MealsCategoriesScreen() {
                Log.d("MealsApp", "FoodId: $it")
                navController.navigate("meals_detail/$it")
            }
        }
        composable(
            route = "meals_detail/{fId}",
            arguments = listOf(navArgument("fId") {
                type = NavType.StringType
            })
        ) {
            Log.d("MealsApp", "id: $it")
            val viewModel: MealsDetailViewModel = viewModel()

            viewModel.mealsState.value?.let { mealResponse -> MealDetailScreen(mealResponse) }
        }
    }

}

