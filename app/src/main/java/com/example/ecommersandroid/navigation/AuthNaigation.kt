package com.example.ecommersandroid.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommersandroid.data.AccoutDetails
import com.example.ecommersandroid.screens.EmailVarifiy
import com.example.ecommersandroid.screens.SignIn.SignInScreen
import com.example.ecommersandroid.screens.SignIn.SignInViewModel
import com.example.ecommersandroid.screens.SignupScreen.SingUpScreen
import com.example.ecommersandroid.screens.TellAboutYou
import com.example.ecommersandroid.screens.forgot.ForgotScreen
import com.example.ecommersandroid.utils.CustomLoder
import com.example.ecommersandroid.utils.DataStorePreference
import com.example.ecommersandroid.utils.ErrorHandling
import com.example.ecommersandroid.utils.NetworkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AuthNaigation(modifier: Modifier = Modifier) {
    val isEamilVarify = checkLogin()
    val destination = if(isEamilVarify == "emailVarify") NaivgationScreenConst.Main.route
    else if(isEamilVarify == "emailNotVarify") NaivgationScreenConst.EmailVarify.route
    else NaivgationScreenConst.SignIn.route
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
fun checkLogin(): String {
    var isEamilVarify by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dataStore = DataStorePreference(context)
    val viewModel = hiltViewModel<SignInViewModel>()
    val response = viewModel.accountDetails.collectAsState()
    val tokeUpate = viewModel.tokenUpdate.collectAsState()
    var token by remember { mutableStateOf("") }
    LaunchedEffect(response) {
        withContext(Dispatchers.IO) {
             token = dataStore.getData("token")
            Log.e("check", "token: " + token)
            if (token.isNotEmpty()) {
                viewModel.accountDetails(token)
            }
        }
    }

        if(isEamilVarify == "" && token.isNotEmpty()) {
          CustomLoder()
        }

        Column {
            when (response.value) {
                is NetworkCall.Error -> {
                    if (response.value is NetworkCall.Error){
                       if(response.value.toString().contains("INVALID_ID_TOKEN")) {
                           runBlocking {
                               val refrshToken = dataStore.getData("refreshToken")
                               isEamilVarify = "tokenUpdate"
                               viewModel.tokenUpdate(refrshToken)
                           }
                       } else {
                           isEamilVarify = "error"
                       }
                    }
                }
                NetworkCall.Loading -> {

                }
                is NetworkCall.Success<*> -> {
                   if(response.value is NetworkCall.Success) {
                       val data = (response.value as NetworkCall.Success<AccoutDetails>).response
                       if(data.users.get(0).emailVerified) {
                       isEamilVarify = "emailVarify"
                       } else {
                           isEamilVarify = "emailNotVarify"
                       }
                   }
                }
            }
            when(tokeUpate.value){
                is NetworkCall.Error -> {
                    Log.e("check", "checkLogin: "+tokeUpate.value)
                    isEamilVarify = "error"
                }
                NetworkCall.Loading -> {
//                    CustomLoder()
                }
                is NetworkCall.Success<*> -> {
                    val response = (tokeUpate.value as NetworkCall.Success<Any?>).response
                    val jsonObj = JSONObject(response.toString())
                    val token = jsonObj.getString("id_token")
                    val refreshToken = jsonObj.getString("refresh_token")
                   runBlocking {
                       dataStore.setData("token",token)
                       dataStore.setData("refreshToken", refreshToken)
                   }
                    viewModel.accountDetails(token)
                }
            }
        }

    return  isEamilVarify
}


//
//if(account.users.get(0).emailVerified) {
//    navController.navigate(NaivgationScreenConst.TellAoutYou.route)
//} else{
//    navController.navigate(NaivgationScreenConst.EmailVarify.route)
//}



