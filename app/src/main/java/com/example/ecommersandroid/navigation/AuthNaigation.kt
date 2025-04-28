package com.example.ecommersandroid.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommersandroid.screens.EmailVarifiy
import com.example.ecommersandroid.screens.SignIn.SignInScreen
import com.example.ecommersandroid.screens.SignIn.SignInViewModel
import com.example.ecommersandroid.screens.SignupScreen.SingUpScreen
import com.example.ecommersandroid.screens.TellAboutYou
import com.example.ecommersandroid.screens.forgot.ForgotScreen
import com.example.ecommersandroid.utils.CustomLoder
import com.example.ecommersandroid.utils.DataStorePreference
import com.example.ecommersandroid.utils.NetworkCall

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AuthNaigation(modifier: Modifier = Modifier) {
    val isLogedIn = checkLogin()
    val destination = if(isLogedIn) NaivgationScreenConst.Main.route else NaivgationScreenConst.SignIn.route
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = destination){
        composable(NaivgationScreenConst.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(NaivgationScreenConst.SignUp.route) {
            SingUpScreen(navController = navController)
        }
        composable(NaivgationScreenConst.Forgot.route) {
            ForgotScreen(navController = navController)
        }
        composable(NaivgationScreenConst.TellAoutYou.route) {
            TellAboutYou(navController = navController)
        }
        composable(NaivgationScreenConst.Main.route) {
            MainScreen()
        }
        composable("email_varify/{idToken}") { backStack ->
            val idToken = backStack.arguments?.getString("idToken")
            EmailVarifiy(navController= navController, idToken = idToken.toString())
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun checkLogin() : Boolean {
    val dataStore = DataStorePreference(LocalContext.current)
    val viewModel = hiltViewModel<SignInViewModel>()
    val response = viewModel.accountDetails.collectAsState()
    LaunchedEffect(response) {
        val token = dataStore.getData("token")
        val rereshToken = dataStore.getData("rereshToken")
        Log.e("check", "checkLogin: "+token)
        Log.e("check", "checkLogin: "+rereshToken)
        if (token.isNotEmpty()) {
            viewModel.accountDetails(token)
        }
    }

        Column {
            when (response) {
                is NetworkCall.Success<*> -> {
                   return true
                }

                is NetworkCall.Error -> {
                    return false
                }

                is NetworkCall.Loading -> {
                    CustomLoder()
                }
            }
        }

    return  false
}







