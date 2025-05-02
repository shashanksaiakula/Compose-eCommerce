package com.example.ecommersandroid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommersandroid.navigation.Screen
import com.example.ecommersandroid.R
import com.example.ecommersandroid.navigation.NaivgationScreenConst
import com.example.ecommersandroid.utils.DataStorePreference
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(navController: NavController,modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dataStore = DataStorePreference(context)
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle),
                contentDescription = "icon",
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(50.dp)),
                contentScale = ContentScale.FillBounds
            )
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Name", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp, 5.dp)
                        )
                        Text(
                            "Gmail", fontSize = 18.sp, fontWeight = FontWeight.W100,
                            modifier = Modifier.padding(5.dp, 5.dp)
                        )
                        Text(
                            "phone number", fontSize = 18.sp, fontWeight = FontWeight.W200,
                            modifier = Modifier.padding(5.dp, 5.dp)
                        )
                    }
                    Text("Edit", color = colorResource(R.color.ylate))
                }
            }
            ClickableCard("Address"){
                navController.navigate(Screen.Address.route)
            }
            ClickableCard("Wishlist"){

            }
            ClickableCard("Payment"){

            }
            ClickableCard("Help"){

            }
            ClickableCard("Support"){

            }
        }
        Text("Sign Out", fontWeight = FontWeight.Bold, color = Color.Red, fontSize = 20.sp, modifier = Modifier.clickable() {
            runBlocking {
                dataStore.clearDataStore(context)
            }
            navController.navigate(NaivgationScreenConst.SignIn.route)
        })
    }
}

@Composable
fun ClickableCard(title :String, onClick :() -> Unit) {
//    Row {
    Card(
        modifier = Modifier
            .padding(8.dp,8.dp)
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ){
                Row(
                    Modifier.fillMaxWidth().padding(10.dp,10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(title)
                    Image(imageVector = Icons.Default.NavigateNext, contentDescription = "")
                }
            }
//}
}