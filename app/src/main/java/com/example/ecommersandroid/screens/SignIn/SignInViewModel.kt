package com.example.ecommersandroid.screens.SignIn

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

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

    fun signIn() {
            Log.d("check", "signIn: ${this.email.value} \n ${this.password.value}")
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