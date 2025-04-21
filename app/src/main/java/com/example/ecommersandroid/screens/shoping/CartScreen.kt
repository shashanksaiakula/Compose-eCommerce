package com.example.ecommersandroid.screens.shoping

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommersandroid.Navigation.BottomNavigationScreen
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton
import com.example.ecommersandroid.components.CustomEditField

@Composable
fun CartScreen(navController: NavController,modifier: Modifier = Modifier) {
    BackHandler {  }
    Column(
        modifier = Modifier.padding(10.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painterResource(R.drawable.back_icon), contentDescription = "back button",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigate(
                            BottomNavigationScreen.BottomNav.route
                        )
                    })
            Text("Cart", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("")
        }
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            items(20, key = { it }) {
                CartComponet()
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.inverseSurface, thickness = 1.dp)
        RowText("Subtotel", "200")
        RowText("Shipping Cost", "8.00")
        RowText("Tax","0.00")
        RowText("Total","208")
        CustomEditField(value = "", onValueChange = {}, placeholder = "Enter Coupon Code", error = false, isPassword = false)
        CustomButton(buttonText = "Checkout",
            textColor = Color.White,
            buttonBackGroundColor = colorResource(
                R.color.ylate
            )
        ){

        }
    }
}

@Composable
fun CartComponet() {
    var itemCount by remember { mutableStateOf(1) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = colorResource(R.color.buttom_backGround),
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle), contentDescription = "",
                modifier = Modifier.size(100.dp).clip(shape = RoundedCornerShape(25.dp))
            )
            Column(
                modifier = Modifier
            ) {
                Text("Name")
                Text("Barand")
                Text("Price")
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                   itemCount= itemCount.plus(1)
                },
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .shadow(elevation = 0.dp, shape = RoundedCornerShape(10.dp)),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = colorResource(R.color.ylate)
                )
            ) {
                Text("+", textAlign = TextAlign.Center, fontSize = 20.sp,)
            }
            Text(
                itemCount.toString(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    if(itemCount >1) {
                        itemCount = itemCount.minus(1)
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .shadow(elevation = 0.dp, shape = RoundedCornerShape(10.dp)),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = colorResource(R.color.ylate)
                )
            ) {
                Text("-", textAlign = TextAlign.Center, fontSize = 20.sp,)
            }
        }
    }
}

@Composable
fun RowText(title: String, price :String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text("$ $price", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
