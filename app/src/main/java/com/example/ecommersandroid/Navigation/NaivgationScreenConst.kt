package com.example.ecommersandroid.Navigation

sealed class NaivgationScreenConst(val route: String) {
    object SignIn : NaivgationScreenConst("signIn")
    object SignUp : NaivgationScreenConst("signUp")
    object TellAoutYou : NaivgationScreenConst("tellAoutYou")
    object Forgot : NaivgationScreenConst("forgot")
    object Main : NaivgationScreenConst("main")
    class Secreen2 : NaivgationScreenConst("screen2/{isClicked}")
}

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Search : Screen("search")
    object Cart: Screen("cart_screen")
    object ListCategaries : NaivgationScreenConst("list_categaries")
    object Address :  Screen("address")
}

sealed class BottomNavigationScreen(val route: String) {
    object BottomNav : BottomNavigationScreen("bottom_nav")
}
