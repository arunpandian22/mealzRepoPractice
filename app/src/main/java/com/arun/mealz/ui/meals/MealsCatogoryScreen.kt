package com.arun.mealz.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.arun.mealz.model.MealsResponse
import com.arun.mealz.ui.theme.MealzTheme


/**
 * Created by Arun Pandian  on 25/07/22.
 */
/**
 * A Compose function for the listing meals list
 */
@Composable
fun MealsCategoriesScreen(navigationCallBack: (String) -> Unit) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { _mealsResponse ->
            MealCategory(_mealsResponse, navigationCallBack)
        }
    }
}

@Composable
fun MealCategory(mealsResponse: MealsResponse, navigationCallBack: (String) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { navigationCallBack.invoke(mealsResponse.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            val request =
                ImageRequest.Builder(LocalContext.current).data(mealsResponse.imageUrl).build()
            Image(
                rememberAsyncImagePainter(model = request),
                contentDescription = "",
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(alignment = Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = mealsResponse.name, style = MaterialTheme.typography.h6)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = mealsResponse.description,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 4,
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Start
                    )
                }
            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        alignment = if (isExpanded) Bottom
                        else CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MealzTheme {
        MealsCategoriesScreen(){

        }
    }
}