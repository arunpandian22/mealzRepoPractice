package com.arun.mealz.ui.detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.arun.mealz.model.MealsResponse


/**
 * Created by Arun Pandian  on 25/07/22.
 */

@Composable
fun MealDetailScreen(mealsResponse: MealsResponse) {
    var profilePictureState by remember {
        mutableStateOf(MealProfilePictureState.Normal)
    }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSize: Dp by transition.animateDp(targetValueByState = { _profilePictureState ->
        _profilePictureState.size
    }, label = "")
    val color by transition.animateColor(targetValueByState = { _profilePictureState ->
        _profilePictureState.color
    }, label = "")
    val widthSize: Dp by transition.animateDp(targetValueByState = { _profilePictureState ->
        _profilePictureState.borderWidth
    }, label = "")
    Column() {
        Row() {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(widthSize, color)
            ) {
                val request =
                    ImageRequest.Builder(LocalContext.current).data(mealsResponse.imageUrl)
                        .transformations(
                            CircleCropTransformation()
                        ).build()
                Image(
                    rememberAsyncImagePainter(model = request),
                    contentDescription = "", modifier = Modifier
                        .size(imageSize)
                        .padding(8.dp)
                )
            }
            Text(
                text = mealsResponse.name, modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        Button(onClick = {
            profilePictureState =
                if (profilePictureState == MealProfilePictureState.Normal) MealProfilePictureState.Expanded else
                    MealProfilePictureState.Normal
        }) {
            Text(text = "Change state of profile picture")

        }
    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Cyan, 120.dp, 4.dp),
    Expanded(Color.Green, 200.dp, 8.dp)
}