package com.example.ecommersandroid

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ecommersandroid.Navigation.AuthNaigation
import com.example.ecommersandroid.screens.AddressScreen
import com.example.ecommersandroid.ui.theme.EcommersAndroidTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            EcommersAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    AuthNaigation(modifier = Modifier.padding(innerPadding))
//                    MainScreen(modifier = Modifier.padding(innerPadding))
//                    CartScreen(modifier = Modifier.padding(innerPadding))
                    AddressScreen(navController = null, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
