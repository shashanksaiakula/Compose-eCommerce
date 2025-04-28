package com.example.ecommersandroid.screens.shoping

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecommersandroid.navigation.Screen
import com.example.ecommersandroid.R
import com.example.ecommersandroid.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("Women") }
    var productList = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        val jsonContent = context.assets.open("product.json").bufferedReader().use { it.readText() }
        val productListType = object : TypeToken<List<Product>>() {}.type
        val products: List<Product> = Gson().fromJson(jsonContent, productListType)
        productList.addAll(products)

    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle),
                contentDescription = "icon",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.FillBounds
            )
            IconButton(
                onClick = { isExpanded = !isExpanded },
                Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth(0.4f)
                    .padding(10.dp, 0.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = gender,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(R.drawable.down_arroew),
                        contentDescription = "icon"
                    )
                }
            }
            IconButton(
                onClick = { },
                Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(color = colorResource(R.color.ylate))
                    .size(50.dp)
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }

            DropdownMenu(
                modifier = Modifier,
                expanded = isExpanded,
                offset = DpOffset(130.dp, 180.dp),
                onDismissRequest = { isExpanded = false },
            ) {
                DropdownMenuItem(text = { Text("Men",textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()) }, onClick = {
                        gender = "Men"
                        isExpanded = false })
                DropdownMenuItem(text = { Text("Women",textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()) }, onClick = {
                        gender = "Women"
                        isExpanded = false })
            }

        Spacer(modifier = Modifier.height(10.dp))
        CategoriesComp(navController = navController)
        Spacer(modifier = Modifier.height(10.dp))
        ShowItems(productList,navController)
    }

}

@Composable
fun ShowItems(productList: SnapshotStateList<Product>, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        val colorStops = listOf(
            colorResource(R.color.ylate),
            colorResource(R.color.white)
        )
        Text(
            text = "Find your like items",
            style = TextStyle(
//                shadow = Shadow(
//                    offset = Offset(10f,10f),
//                    blurRadius = 1f,
//                    color = Red
//                ),
                brush = Brush.verticalGradient(
                    colors = colorStops,
                    tileMode = TileMode.Mirror
                )
            ),
            fontSize = 26.sp

            )

    }

//    LazyVerticalGrid(
//        modifier = Modifier,
//        columns = GridCells.Fixed(2),
////        rows = GridCells.Fixed(2),
////        horizontalArrangement = Arrangement.spacedBy(10.dp),
////        verticalArrangement = Arrangement.spacedBy(10.dp),
////        contentPadding = PaddingValues(10.dp)
//    ) {
//        items(
//            count = 30, key = {it}){
//            Image(
//                painter = painterResource(R.drawable.rectangle),
//                contentDescription = "icon",
//                modifier = Modifier.fillMaxWidth(.5f).padding(10.dp),
//                contentScale = ContentScale.FillBounds
//            )
//        }
//    }
    LazyColumn(
    ) {
        items(productList.chunked(2)) {
            Row {
                it.forEach { product ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color.Gray)
                            .height(250.dp)
                            .clickable {
                                navController.navigate("detail_screen/${product.id}")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = product.thumbnail,
                            contentDescription = "icon",
                            modifier = Modifier
                                .height(120.dp)
                                .padding(10.dp)
                                .weight(1f),
                            contentScale = ContentScale.Fit,
                            onError = { error ->
                                Log.e("ImageLoading", "Error loading image: ${error.result.throwable}")
                            }
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(color = MaterialTheme.colorScheme.inverseOnSurface),
                        ) {
                            Text(product.title, modifier = Modifier.padding(5.dp), maxLines = 1,
                                overflow = TextOverflow.Ellipsis)
                            Text("$${product.price}", modifier = Modifier.padding(3.dp), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoriesComp(modifier: Modifier = Modifier, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        navController.navigate(Screen.ListCategaries.route)
                    }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
        )  {
            items(
                count = 200, key = {it}){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.rectangle),
                        contentDescription = "icon",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(30.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    Text("Categories", modifier = Modifier.padding(0.dp,10.dp),
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }





//CartScreen.kt

//SettingScreen
@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Search Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}