package com.example.ecommersandroid.screens

import android.os.Build
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.ecommersandroid.navigation.NaivgationScreenConst
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TellAboutYou(modifier: Modifier = Modifier, navController: NavController) {

    var buttonSelected by remember { mutableStateOf(0) }

    var age by remember { mutableStateOf(0) }
    val context = LocalContext.current
BackHandler { }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(stringResource(R.string.tell_us_more))
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CustomButton(
                    modifier = Modifier.weight(1f),
                    buttonBackGroundColor = if (buttonSelected == 1) colorResource(id = R.color.ylate) else colorResource(
                        id = R.color.black
                    ),
                    textColor = colorResource(id = R.color.white),
                    buttonText = stringResource(R.string.men),
                    onClick = {
                        buttonSelected = 1
                    }
                )
                CustomButton(
                    modifier = Modifier.weight(1f),
                    buttonBackGroundColor = if (buttonSelected == 2) colorResource(id = R.color.ylate) else colorResource(
                        id = R.color.black
                    ),
                    textColor = colorResource(id = R.color.white),
                    buttonText = stringResource(R.string.women),
                    onClick = {
                        buttonSelected = 2
                    }
                )
            }
            Text(stringResource(R.string.how_old_are_you))
            Spacer(modifier = Modifier.height(10.dp))
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth(),
                factory = { context ->
                    NumberPicker(context).apply {
                        setOnValueChangedListener { numberPicker, i, i2 ->
                            age = i2
                        }
                        minValue = 18
                        maxValue = 100
                        textColor = getResources().getColor(R. color. white)
                    }
                }
            )
        }

        CustomButton(
            buttonBackGroundColor =  colorResource(id = R.color.ylate),
            textColor = colorResource(id = R.color.white),
            buttonText = stringResource(R.string.finish),
            onClick = {
                if(buttonSelected == 0 || age == 0){
                    Toast.makeText(context, "Please select your gender or age", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(context, "Your gender is $buttonSelected and your age is $age", Toast.LENGTH_SHORT).show()
//                    Handler(Looper.getMainLooper()).postDelayed({
                        navController.navigate(NaivgationScreenConst.Main.route) {
                            popUpTo(NaivgationScreenConst.SignIn.route) { inclusive = true }
                        }
//                    }, 500)
                }
            }
        )
    }
}