package com.example.ecommersandroid.screens.SignIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommersandroid.AuthFLowServices.AuthFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    // https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyBaphpxfe2Wz5FjEpQtDfI9dZSCk2bY6DE
    val retrofit = Retrofit.Builder()
        .baseUrl("https://identitytoolkit.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()




    fun validateEmail(email: String): Boolean {
        if(email.isEmpty()) return false
        _email.value = email
        return email.contains("@") && email.contains(".")
    }

    fun validatePassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }
                .firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }
                .firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        _password.value = password
        return true
    }

    fun signIn() : Boolean {
        var isSuccess = false
        val body = HashMap<String, String>()
        body["email"] = email.value
        body["password"] = password.value
        body["returnSecureToken"] = "true"
        viewModelScope.launch {
           val response = retrofit.create(AuthFlow::class.java).signInWithPassword(
               "AIzaSyBaphpxfe2Wz5FjEpQtDfI9dZSCk2bY6DE",
              body
           )
            if(response.isSuccessful) {
                Log.d("check", "signIn: ${response.body()}")
               isSuccess = true
            }else if(response.errorBody() != null)
                Log.e("check", "signIn: ${response.errorBody()!!.string()}")
                isSuccess = true
        }
       return isSuccess
    }

    fun validatFirname(fname: String): Boolean {
        if(fname.isEmpty()) return false
        if(fname.length < 2) return false
        return true
    }
    fun validatLname(lname: String): Boolean {
        if(lname.isEmpty()) return false
        if(lname.length < 2) return false
        return true
    }

    fun sinUp() {
        Log.d("check", "signIn: ${this.email.value} \n ${this.password.value}")
    }



}