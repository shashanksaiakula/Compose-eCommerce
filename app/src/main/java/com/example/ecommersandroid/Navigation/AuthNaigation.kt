package com.example.ecommersandroid.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommersandroid.screens.SignIn.SignInScreen
import com.example.ecommersandroid.screens.SignupScreen.SingUpScreen
import com.example.ecommersandroid.screens.TellAboutYou
import com.example.ecommersandroid.screens.forgot.ForgotScreen
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AuthNaigation(modifier: Modifier = Modifier, isLogedIn :Boolean =false) {
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
    }
}







