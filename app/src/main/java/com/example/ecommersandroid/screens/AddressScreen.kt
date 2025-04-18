package com.example.ecommersandroid.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton
import com.example.ecommersandroid.components.CustomEditField

@Composable
fun AddressScreen(navController : NavController?, modifier: Modifier = Modifier) {

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Image(
//                painterResource(R.drawable.back_icon), contentDescription = "back button",
//                modifier = Modifier
//                    .size(50.dp)
//                    .clickable {
//                        navController?.popBackStack()
//                    })
//            Text(
//                stringResource(R.string.add_address),
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(10.dp),
//                fontSize = 22.sp
//            )
//            Text("")
//        }
//        CustomEditField(
//            value = "",
//            onValueChange = {
//                Log.e("check", "AddressScreen: $it", )
//            }, placeholder = "Street Address",
//            isPassword = false,
//            error = false
//        )
//        CustomEditField(
//            value = "",
//            onValueChange = {
//                Log.e("check", "AddressScreen: $it", )
//            }, placeholder = "City",
//            isPassword = false,
//            error = false
//        )
//        Row{
//            CustomEditField(
//                modifier = Modifier.weight(1f),
//                value = "",
//                onValueChange = {
//                    Log.e("check", "AddressScreen: $it", )
//                }, placeholder = "State",
//                isPassword = false,
//                error = false
//
//            )
//            CustomEditField(
//                modifier = Modifier.weight(1f),
//                value = "",
//                onValueChange = {
//                    Log.e("check", "Zip Coade: $it", )
//                }, placeholder = "Zip Coade",
//                isPassword = false,
//                error = false
//            )
//        }
//        CustomButton(
//            buttonText = "Add address",
//            textColor = MaterialTheme.colorScheme.inverseSurface,
//            buttonBackGroundColor = MaterialTheme.colorScheme.primary
//        ) {
//
//        }
//                LazyColumn {
//                    items(100, key ={it} ){
//                        Card(
//                            modifier = Modifier
//                                .padding(8.dp,8.dp)
//                                .border(
//                                    width = 1.dp,
//                                    color = Color.Transparent,
//                                    shape = RoundedCornerShape(10.dp)
//                                )
//                        )
//                        {
//                            Row(
//                                modifier = Modifier.fillMaxWidth().padding(20.dp,12.dp),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ){
//                                Text("address $it", modifier)
//                                Row {
//                                   Icon(imageVector = Icons.Default.Edit, contentDescription = "edit", modifier = Modifier
//                                       .clickable {  }
//                                       .background(color = colorResource(R.color.buttom_backGround), shape = RectangleShape)
//                                       .padding(10.dp))
//                                    Spacer(Modifier.width(10.dp))
//                                   Icon(imageVector = Icons.Default.Delete, contentDescription = "delete",
//                                       modifier = Modifier
//                                           .clickable {  }
//                                           .background(color = colorResource(R.color.buttom_backGround), shape = RectangleShape)
//                                           .padding(10.dp))
//                                }
//
//                    }
//                }
//            }
//        }
//    }


    var listItem = remember { mutableStateListOf<String> (
        "apple",
        "banana",
        "cherry",
        "date",
        "elderberry",
        "fig",
        "grape",
        "honeydew",
        "kiwi",
        "lemon",
        "mango",
        "nectarine",
        "orange",
        "peach",
        "quince",
        "raspberry",
        "strawberry",
        "tangerine",
        "watermelon"
    )
    }


    Column {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            items(listItem.size) { index ->
                Text(text = listItem[index], modifier = Modifier.clickable {
                    listItem.add(listItem.size , listItem[index])
                    listItem.removeAt(index)

                })
            }

        }
//        LazyColumn(
//            modifier = Modifier.weight(1f).fillMaxWidth()
//        ) {
//            items(addedList.size) { index ->
//                Text(text = addedList[index], fontSize = 20.sp)
//                }
//            }
//        }
    }
    }


