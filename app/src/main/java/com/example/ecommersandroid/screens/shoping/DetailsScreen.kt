package com.example.ecommersandroid.screens.shoping

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecommersandroid.navigation.BottomNavigationScreen
import com.example.ecommersandroid.R
import com.example.ecommersandroid.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.ecommersandroid.components.CustomButton


@Composable
fun DetailsScreen(modifier: Modifier = Modifier, id :String, navController: NavController) {

    val context = LocalContext.current
    val productDetails = remember { mutableStateListOf<Product>() }
    var addedItems by remember { mutableStateOf<Int>(0) }
    var stock by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        val jsonContent = context.assets.open("product.json").bufferedReader().use { it.readText() }
        val productListType = object : TypeToken<List<Product>>() {}.type
        var products: List<Product> = Gson().fromJson(jsonContent, productListType)
       products = products.filter { id.toInt() == it.id }
        productDetails.addAll(products)
        stock = productDetails.get(0).stock

    }
    if(productDetails.size >0) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        painterResource(R.drawable.back_icon), contentDescription = "back button",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.navigate(
                                    BottomNavigationScreen.BottomNav.route)
                            })
                    LazyRow(
                        modifier = Modifier
                            .fillMaxHeight(0.4f).padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(
                            productDetails.get(0).images,
                            key = { it },
                        ) {
                            AsyncImage(
                                model = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(5.dp)
                                    .border(
                                        width = 0.1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                contentDescription = "icon",
                                onError = { error ->
                                    Log.e(
                                        "ImageLoading",
                                        "Error loading image: ${error.result.throwable}"
                                    )
                                }
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(productDetails.get(0).title)
                        Text(productDetails.get(0).brand)
                    }
                    Column {
                        Text("$ " + productDetails.get(0).price.toString())
                        Text("$stock In Stock")
                    }
                }
                  val rating = productDetails.get(0).rating
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    for (i in 1..5) {
                        if (i < rating)
                            Icon(imageVector = Icons.Default.Star, "rating", tint = Color.Yellow)
                        else
                            Icon(imageVector = Icons.Default.Star, "rating",)
                    }
                    Text(rating.toInt().toString())
                }

                Text("Review", fontSize = 40.sp)
                HorizontalDivider(Modifier.fillMaxWidth().height(2.dp))
                LazyColumn(
                    modifier = Modifier.padding(10.dp).padding(bottom = 50.dp)
                ) {
                    items(
                        productDetails.get(0).reviews,
                    )
                    {
                        Row {
                            val rating = it.rating
                            for (i in 1..5) {
                                if (i <= rating)
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        "rating",
                                        tint = Color.Yellow
                                    )
                                else
                                    Icon(imageVector = Icons.Default.Star, "rating",)
                            }
                            Text(rating.toInt().toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(it.reviewerName)
                                Text(it.reviewerEmail)
                            }
                            Text(it.date, modifier = Modifier.weight(1f))
                        }
                        Text(
                            it.comment,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                }

            }
            CustomButton(
                modifier = Modifier.align(Alignment.BottomCenter), buttonText = if(addedItems > 0  ) "Added to Cart ${addedItems}" else "Add to Cart",
                textColor = Color.White,
                buttonBackGroundColor = colorResource(
                    R.color.ylate,
                )

            ) {
                if(productDetails.get(0).stock > addedItems) {
                    stock -= 1
                    addedItems += 1
                } else
                    Toast.makeText(context, "Reached maximum", Toast.LENGTH_SHORT).show()
            }
        }

    } else{
        CircularProgressIndicator()
    }


}