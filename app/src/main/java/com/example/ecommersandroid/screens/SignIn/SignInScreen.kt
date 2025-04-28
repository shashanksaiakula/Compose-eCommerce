package com.example.ecommersandroid.screens.SignIn

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommersandroid.navigation.NaivgationScreenConst
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton
import com.example.ecommersandroid.components.CustomEditField
import com.example.ecommersandroid.components.IconWithTextComponet
import com.example.ecommersandroid.data.AccoutDetails
import com.example.ecommersandroid.utils.CustomLoder
import com.example.ecommersandroid.utils.DataStorePreference
import com.example.ecommersandroid.utils.NetworkCall


@Composable
fun SignInScreen(modifier: Modifier = Modifier, navController: NavController) {

    val context = LocalContext.current
    BackHandler { }
    val signInViewModel = hiltViewModel<SignInViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonClick by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    val signResponse by signInViewModel.signIn.collectAsState()
    val accoutDetails by signInViewModel.accountDetails.collectAsState()
     val dataStore = DataStorePreference(context)

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
    LaunchedEffect(signResponse) {
        if (signResponse is NetworkCall.Success) {
            val signInData = (signResponse as NetworkCall.Success).response
            dataStore.setData("refreshToken", signInData.refreshToken)
            dataStore.setData("token", signInData.idToken)
            signInViewModel.accountDetails(signInData.idToken)
        } else {
            if (signResponse is NetworkCall.Error) {
                Log.d("check", "SignInScreen: " + signResponse)
                Toast.makeText(context, "${signResponse}", Toast.LENGTH_SHORT,).show()
                isButtonClick = false
            }
        }
    }

    LaunchedEffect(accoutDetails) {
        if(accoutDetails is NetworkCall.Success) {
            val account : AccoutDetails = (accoutDetails as NetworkCall.Success).response
            if(account.users.get(0).emailVerified) {
                navController.navigate(NaivgationScreenConst.TellAoutYou.route)
            } else{
                navController.navigate(NaivgationScreenConst.EmailVarify.route)
            }
        } else if(accoutDetails is NetworkCall.Error) {
            val error = (accoutDetails as NetworkCall.Error).error
            Toast.makeText(context,error.toString(), Toast.LENGTH_SHORT).show()
            isButtonClick = false
        }
    }

        if ((signResponse is NetworkCall.Loading || accoutDetails is NetworkCall.Loading) && isButtonClick) {
            CustomLoder()
    }
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.sing_in),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
            fontSize = 22.sp
        )
        Spacer(Modifier.height(50.dp))
        CustomEditField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = stringResource(R.string.email_address),
            isPassword = false,
            error = isEmailValid
        )
        CustomEditField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = stringResource(R.string.password),
            isPassword = true,
            error = isPasswordValid
        )
        CustomButton(
            buttonText = stringResource(R.string.sing_in),
            textColor = Color.White,
            buttonBackGroundColor = colorResource(
                R.color.ylate
            )
        ) {
            isButtonClick = true
            isEmailValid = !signInViewModel.validateEmail(email)
            isPasswordValid = !signInViewModel.validatePassword(password)
            if (!isEmailValid && !isPasswordValid) {
                signInViewModel.signIn()
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                stringResource(R.string.don_t_have_an_account) + " ", fontSize = 14.sp
            )
            Text(
                stringResource(R.string.create_one),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(NaivgationScreenConst.SignUp.route)
                })

        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                stringResource(R.string.forget_password) + " ", fontSize = 14.sp
            )
            Text(
                stringResource(R.string.reset),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(NaivgationScreenConst.Forgot.route)
                })
        }
        Spacer(Modifier.height(50.dp))
        IconWithTextComponet(
            text = stringResource(R.string.continue_with_apple), iamge = R.drawable.apple
        ) {}
        IconWithTextComponet(
            text = stringResource(R.string.continue_with_google), iamge = R.drawable.google
        ) {}
        IconWithTextComponet(
            text = stringResource(R.string.continue_with_facebook), iamge = R.drawable.facebook
        ) {}
    }
}