package com.arun.mealz.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.arun.mealz.model.MealsResponse


/**
 * Created by Arun Pandian  on 25/07/22.
 */

@Composable
fun MealDetailScreen(mealsResponse: MealsResponse) {
    Column() {
        Row() {
            Card() {
                val request =
                    ImageRequest.Builder(LocalContext.current).data(mealsResponse.imageUrl)
                        .transformations(
                            CircleCropTransformation()
                        ).build()
                Image(
                    rememberAsyncImagePainter(model = request),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
            }
            Text(text = mealsResponse.name)
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Change state of profile picture")
            
        }
    }
}