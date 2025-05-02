package com.example.ecommersandroid.screens.SignupScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.ecommersandroid.data.SingIn
import com.example.ecommersandroid.screens.SignIn.SignInViewModel
import com.example.ecommersandroid.utils.CustomLoder
import com.example.ecommersandroid.utils.NetworkCall

@SuppressLint("SuspiciousIndentation")
@Composable
fun SingUpScreen(modifier: Modifier = Modifier,navController: NavController) {

    val signInViewModel = hiltViewModel<SignInViewModel>()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonClick by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    var isFirstNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }

    val signUp by signInViewModel.sinUp.collectAsState()
    val profile by signInViewModel.profile.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(email) {
        if (email.isNotEmpty()) {
            isEmailValid = !signInViewModel.validateEmail(email)
        } else {
            isEmailValid = false
        }
    }

    LaunchedEffect(password) {
        if (password.isNotEmpty()) {
            isPasswordValid = !signInViewModel.validatePassword(password)
        } else {
            isPasswordValid = false
        }
    }
    LaunchedEffect(firstName) {
        if (firstName.isNotEmpty()) {
            isFirstNameValid = !signInViewModel.validatFirname(firstName)
        } else {
            isFirstNameValid = false
        }
    }
    LaunchedEffect(lastName) {
        if (lastName.isNotEmpty()) {
            isLastNameValid = !signInViewModel.validatLname(lastName)
        } else {
            isLastNameValid = false
        }
    }

    LaunchedEffect(signUp) {
       if(signUp is NetworkCall.Success) {
         val loginData = (signUp as NetworkCall.Success).response
           signInViewModel.updateUserProfile(idToken = loginData.idToken, refeshToken = loginData.refreshToken, name = "${firstName} ${lastName}" )
       } else if(signUp is NetworkCall.Error) {
           Toast.makeText(context,signUp.toString(), Toast.LENGTH_SHORT).show()
       }
    }
    LaunchedEffect(profile) {
        if(profile is NetworkCall.Success){
            val response = (profile as NetworkCall.Success).response
            val loginData = (signUp as NetworkCall.Success).response
            Log.e("check", "SingUpScreen: "+response)
            navController.navigate("email_varify/${loginData.idToken}")
        } else if(profile is NetworkCall.Error) {
            val message = (profile as NetworkCall.Error).error
            Log.e("check", "SingUpScreen: "+message)
            Toast.makeText(context,message?.message,Toast.LENGTH_SHORT).show()
        }

    }

    BackHandler { }
if((signUp is NetworkCall.Loading || profile is NetworkCall.Loading) && isButtonClick){
    CustomLoder()
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
            Image(painterResource(R.drawable.back_icon), contentDescription = "back button",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        navController.popBackStack()
                    })
        }


        Text(
            stringResource(R.string.create_accout),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
            fontSize = 22.sp
        )
        Spacer(Modifier.height(50.dp))

        CustomEditField(
            value = firstName, onValueChange = {
                firstName = it
            }, placeholder = stringResource(R.string.first_name), isPassword = false,
            error = isFirstNameValid
        )
        CustomEditField(
            value = lastName, onValueChange = {
                lastName = it
            }, placeholder = stringResource(R.string.last_name), isPassword = false,
            error = isLastNameValid
        )
        CustomEditField(
            value = email, onValueChange = {
                email = it
            }, placeholder = stringResource(R.string.email_address), isPassword = false,
            error = isEmailValid
        )
        CustomEditField(
            value = password, onValueChange = {
                password = it
            }, placeholder = stringResource(R.string.password), isPassword = true,
            error = isPasswordValid
        )
        CustomButton(
            buttonText = stringResource(R.string.sing_up),
            textColor = Color.White,
            buttonBackGroundColor = colorResource(
                R.color.ylate
            )
        ) {

            isEmailValid = !signInViewModel.validateEmail(email)
            isPasswordValid = !signInViewModel.validatePassword(password)
            isLastNameValid = !signInViewModel.validatLname(lastName)
            isFirstNameValid = !signInViewModel.validatFirname(firstName)
            if (!isEmailValid && !isPasswordValid && !isFirstNameValid && !isLastNameValid) {
                isButtonClick = true
                signInViewModel.sinUp()
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                stringResource(R.string.forget_password) + " ",
                fontSize = 14.sp
            )
            Text(
                stringResource(R.string.reset), fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(NaivgationScreenConst.Forgot.route)
                })
        }
        Spacer(Modifier.height(50.dp))

    }
}