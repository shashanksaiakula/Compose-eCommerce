package com.example.ecommersandroid.screens

import android.health.connect.datatypes.units.Length
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ecommersandroid.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGenerator(modifier: Modifier = Modifier) {

    var length by remember { mutableStateOf("") }
    var upperCase by remember { mutableStateOf(false) }
    var loweCase by remember { mutableStateOf(false) }
    var special by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf(false) }
    var passwod by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }


    fun generatePasswod(length: Int) {
        var generatePass = ""
        if(upperCase) generatePass += "ABCDEFGHIJKLMNPQRSTVUWXYZ"
        if(loweCase) generatePass += "abcdefghijklmnopqrstuvwxyz"
        if(special) generatePass += " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
        if(number) generatePass += "0123456789"
        if(generatePass.isEmpty()) {
            passwod = "please select at least one option"
            return
        }
        var result =""
        Log.e("check", "generatePasswod: "+generatePass)
        for (a in length downTo 1) {
            val randomIndex = Math.floor(Math.random() * generatePass.length).toInt()
            result += generatePass[randomIndex]
        }
            Log.e("check", "generatePasswod: "+result)
        passwod = result
    }


    Column {
        Text("Password Generator", style = MaterialTheme.typography.headlineMedium,modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "Password Length",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(10.dp)
                )
                if(error.equals("false"))
                Text("Please enter length", style = MaterialTheme.typography.bodySmall,modifier = Modifier.padding(10.dp), color = Color.Red)
            }
            TextField(
                modifier = Modifier.padding(10.dp),
                value = length.toString(),
                placeholder = { Text("Ex 8") },
                onValueChange = {
                    if (it.length <= 2)
                    length = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = VisualTransformation.None,

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Uppdercase Include", style = MaterialTheme.typography.headlineSmall,modifier = Modifier.padding(10.dp))
            Checkbox( checked = upperCase, onCheckedChange = {
                upperCase =it
            },
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = CircleShape)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Lowercase Include", style = MaterialTheme.typography.headlineSmall,modifier = Modifier.padding(10.dp))
            Checkbox( checked = loweCase, onCheckedChange = {
                loweCase =it
            },
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = CircleShape)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Special Char Include", style = MaterialTheme.typography.headlineSmall,modifier = Modifier.padding(10.dp))
            Checkbox( checked = special, onCheckedChange = {
                special =it
            },
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = CircleShape)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Numbers Include", style = MaterialTheme.typography.headlineSmall,modifier = Modifier.padding(10.dp))
            Checkbox( checked = number, onCheckedChange = {
                number =it
            },
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10))
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .clickable {
                        Log.e("check", "PasswordGenerator: " + length)
                        if (length.isNotEmpty()) {
                            generatePasswod(length.toInt())
                            error = "true"
                        } else
                            error = "false"
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Generate", modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.inverseOnSurface)
            }
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .clickable { },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Reset", textAlign = TextAlign.Center,modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.inverseOnSurface)
            }
        }

        if(error == "true")
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(150.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Text("Long press to Cpoy",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(10.dp), color = MaterialTheme.colorScheme.inverseOnSurface)

            Text(text = passwod, style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(10.dp), color = MaterialTheme.colorScheme.inverseOnSurface)

        }

    }
}
