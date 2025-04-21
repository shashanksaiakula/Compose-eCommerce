package com.example.ecommersandroid.screens.shoping

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ecommersandroid.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, id :String) {

    val context = LocalContext.current
    val productDetails = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        val jsonContent = context.assets.open("product.json").bufferedReader().use { it.readText() }
        val productListType = object : TypeToken<List<Product>>() {}.type
        var products: List<Product> = Gson().fromJson(jsonContent, productListType)
       products = products.filter { id.toInt() == it.id }
        productDetails.addAll(products)

    }
    if(productDetails.size >0){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight(0.4f).padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                items(
                    productDetails.get(0).images,
                    key = {it},
                ){
                    Log.e("check", "DetailsScreen: $it")
                    AsyncImage(
                        model = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(5.dp)
                            .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
                        contentDescription = "icon",
                        onError = { error ->
                            Log.e("ImageLoading", "Error loading image: ${error.result.throwable}")
                        }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                    ){
                Column {
                    Text(productDetails.get(0).title)
                    Text(productDetails.get(0).brand)
                }
                Column {
                    Text("$ "+productDetails.get(0).price.toString())
                    Text(productDetails.get(0).stock.toString()+"In Stock")
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
                modifier = Modifier.padding(10.dp)
            ) {
                items(productDetails.get(0).reviews,
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
                        Column {
                            Text(it.reviewerName)
                            Text(it.reviewerEmail)
                        }
                        Text(it.date)
                    }
                    Text(it.comment)
                }
            }

        }
    } else{
        CircularProgressIndicator()
    }


}