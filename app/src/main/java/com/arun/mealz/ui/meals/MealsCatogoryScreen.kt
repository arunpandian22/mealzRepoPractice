package com.arun.mealz.ui.meals

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.arun.mealz.model.MealsResponse
import com.arun.mealz.ui.theme.MealzTheme
import com.arun.mealz.ui.theme.Typography


/**
 * Created by Arun Pandian  on 25/07/22.
 */
/**
 * A Compose function for the listing meals list
 */
@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { _mealsResponse ->
            MealCategory(_mealsResponse)
        }
    }
}

@Composable
fun MealCategory(mealsResponse: MealsResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row() {
            val request =
                ImageRequest.Builder(LocalContext.current).data(mealsResponse.imageUrl)
                    .transformations(
                        RoundedCornersTransformation()
                    ).build()
            Image(
                rememberAsyncImagePainter(model = request),
                contentDescription = "",
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(16.dp)
            ) {
                Text(text = mealsResponse.name, style = MaterialTheme.typography.h6)
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MealzTheme {
        MealsCategoriesScreen()
    }
}