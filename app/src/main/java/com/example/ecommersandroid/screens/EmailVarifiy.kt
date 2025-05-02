package com.example.ecommersandroid.screens

import android.util.JsonToken
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommersandroid.R
import com.example.ecommersandroid.components.CustomButton
import com.example.ecommersandroid.navigation.NaivgationScreenConst
import com.example.ecommersandroid.screens.SignIn.SignInViewModel
import com.example.ecommersandroid.utils.CustomLoder
import com.example.ecommersandroid.utils.DataStorePreference
import com.example.ecommersandroid.utils.NetworkCall

@Composable
fun EmailVarifiy(modifier: Modifier = Modifier, navController: NavController, idToken: String) {
    val viewModel = hiltViewModel<SignInViewModel>()
    val varfiyEmail by viewModel.emaiVaerify.collectAsState()
    val context = LocalContext.current
    var isButtonClick by remember { mutableStateOf(false) }
    val dataStore = DataStorePreference(LocalContext.current)
    LaunchedEffect(Unit) {
        val token = dataStore.getData("token")
        Log.e("check", "checkLogin: "+token)
    }
    LaunchedEffect(Unit) {
        Log.e("check", "checkLogin: "+dataStore.getData("rereshToken"))
    }
    Column (
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            buttonText = "Email Verify",
            textColor = Color.White,
            buttonBackGroundColor = colorResource(
                R.color.ylate
            )
        ) {
            isButtonClick = true
            viewModel.sendEnailVarify(idToken)
        }


        when (varfiyEmail) {
            is NetworkCall.Error -> {
                val error = (varfiyEmail as NetworkCall.Error).error
                Toast.makeText(context, error?.message, Toast.LENGTH_SHORT).toString()
                isButtonClick = false
            }

            NetworkCall.Loading -> {
                if(isButtonClick)
                CustomLoder()
            }

            is NetworkCall.Success<*> -> {
                val response = (varfiyEmail as NetworkCall.Success).response as Map<String, Any>
                Log.d("check", "EmailVarifiy: $response")

                // Extract emailVerified field
                val emailVerified = response["emailVerified"] as? Boolean

                // Check if the email is verified
                if (emailVerified == true) {
                    Log.d("check", "Email is verified")
                    navController.navigate(NaivgationScreenConst.TellAoutYou.route)
                } else {
                    Log.d("check", "Email is not verified")
                }
                isButtonClick = false
            }
        }
    }
}