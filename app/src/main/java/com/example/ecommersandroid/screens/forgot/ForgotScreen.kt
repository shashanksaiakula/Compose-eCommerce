package com.example.ecommersandroid.screens.forgot

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommersandroid.navigation.NaivgationScreenConst
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton
import com.example.ecommersandroid.components.CustomEditField
import com.example.ecommersandroid.screens.SignIn.SignInViewModel

@Composable
fun ForgotScreen(modifier: Modifier = Modifier,navController: NavController) {

    val signInViewModel = hiltViewModel<SignInViewModel>()

    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isButtonClick by remember { mutableStateOf(false) }

BackHandler { }

    LaunchedEffect(email) {
        if (email.isNotEmpty()) {
            isEmailValid = !signInViewModel.validateEmail(email)
        } else {
            isEmailValid = false
        }
    }

    Column(
        modifier = Modifier.padding(10.dp),
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
                        navController.popBackStack()
                    })
        }


        Text(
            stringResource(R.string.forget_password_title),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
            fontSize = 22.sp
        )
        Spacer(Modifier.height(50.dp))

        CustomEditField(
            value = email, onValueChange = {
                email = it
            }, placeholder = stringResource(R.string.email_address), isPassword = false,
            error = isEmailValid
        )
        CustomButton(
            buttonText = stringResource(R.string.continue_),
            textColor = Color.White,
            buttonBackGroundColor = colorResource(
                R.color.ylate
            )
        ) {
            isButtonClick = true
            isEmailValid = !signInViewModel.validateEmail(email)
            if (!isEmailValid ) {
                signInViewModel.sinUp()
                navController.navigate(NaivgationScreenConst.TellAoutYou.route)
            }
        }
    }
}