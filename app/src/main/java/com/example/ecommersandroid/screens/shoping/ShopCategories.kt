package com.example.ecommersandroid.screens.shoping

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommersandroid.navigation.BottomNavigationScreen
import com.example.ecommersandroid.R

@Composable
fun ShopCategories(modifier: Modifier = Modifier,navController: NavController) {

    BackHandler { }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(R.drawable.back_icon), contentDescription = "back button",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigate(
                            BottomNavigationScreen.BottomNav.route)
                    })
        }
        LazyColumn {
            items(20, key = { it }) {
                Row(
                    Modifier.fillMaxWidth()
                        .padding(15.dp, 5.dp)
                        .background(
                            color = colorResource(R.color.buttom_backGround),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(R.drawable.rectangle),
                        modifier = Modifier
                            .size(60.dp).padding(5.dp, 5.dp)
                            .clip(shape = RoundedCornerShape(30.dp)),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                    Text("sdfsdf", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}


