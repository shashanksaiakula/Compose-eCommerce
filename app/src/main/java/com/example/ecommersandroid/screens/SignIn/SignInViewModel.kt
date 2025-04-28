package com.example.ecommersandroid.screens.SignIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.network.NetworkRequestBody
import com.example.ecommersandroid.Repo.AuthFlowImp
import com.example.ecommersandroid.data.AccoutDetails
import com.example.ecommersandroid.data.SingIn
import com.example.ecommersandroid.screens.shoping.HomeScreen
import com.example.ecommersandroid.utils.Constansts
import com.example.ecommersandroid.utils.NetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor(private val authFlowImp: AuthFlowImp)  : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _signIn = MutableStateFlow<NetworkCall<SingIn>>(NetworkCall.Loading)
    val signIn: StateFlow<NetworkCall<SingIn>> = _signIn

    private val _login = MutableStateFlow<NetworkCall<SingIn>>(NetworkCall.Loading)
    val sinUp = _login

    private val _profile = MutableStateFlow<NetworkCall<Any>>(NetworkCall.Loading)
    val profile = _profile

    private val _emaiVaerify = MutableStateFlow<NetworkCall<Any>>(NetworkCall.Loading)
    val emaiVaerify = _emaiVaerify

    private val _accountDetails = MutableStateFlow<NetworkCall<AccoutDetails>>(NetworkCall.Loading)
    val accountDetails = _accountDetails

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

    fun signIn()  {
        val body = HashMap<String, String>()
        body["email"] = email.value
        body["password"] = password.value
        body["returnSecureToken"] = "true"
        viewModelScope.launch {
           val response = authFlowImp.SingIn(
               body,
               Constansts.KEY
           )
            if (response.isSuccessful){
                _signIn.value = response.body()?.let { NetworkCall.Success(it) }!!
            }else{
                _signIn.value = response.errorBody()?.let { NetworkCall.Error(it.string()) }!!
            }
            }
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
           val body = HashMap<String, String>()
           body["email"] = email.value
           body["password"] = password.value
           body["returnSecureToken"] = "true"
       viewModelScope.launch {
           val response= authFlowImp.SingUp(Constansts.KEY,
               body
               )
        if(response.isSuccessful){
           _login.value= response.body()?.let { NetworkCall.Success(it) }!!
        } else{
            _login.value = response.errorBody()?.let { NetworkCall.Error(it.toString()) }!!
        }
       }
    }

    fun updateUserProfile(name : String, refeshToken : String, idToken : String){
            val body = HashMap<String, String>()
            body["idToken"] = idToken
            body["displayName"] = name
            body["grant_type"] = "refresh_token"
            body["refresh_token"] = refeshToken
        viewModelScope.launch {
            val response = authFlowImp.updateUserProfile(key = Constansts.KEY,
                body)
            if(response.isSuccessful){
                _profile.value = response.body()?.let { NetworkCall.Success(it) }!!
            } else{
                _password.value = response.errorBody()?.let { NetworkCall.Error(it.string()) }.toString()
            }
        }
    }

    fun sendEnailVarify(idToken: String) {
        val body = HashMap<String, String>()
        body["requestType"] = "VERIFY_EMAIL"
        body["idToken"] = idToken
        viewModelScope.launch {
            val response = authFlowImp.sendEmailVerfit(Constansts.KEY,body)
            if(response.isSuccessful) {
                _emaiVaerify.value = response.body()?.let { NetworkCall.Success(it) }!!
            } else{
                _emaiVaerify.value = response.errorBody()?.let{ NetworkCall.Error(it.toString()) }!!
            }
        }
    }

    fun accountDetails(idToken: String){
        val body = HashMap<String, String>()
        body["requestType"] = "VERIFY_EMAIL"
        body["idToken"] = idToken
        viewModelScope.launch {
            val response = authFlowImp.accoutDetails(Constansts.KEY,body)
            if(response.isSuccessful){
                _accountDetails.value = response.body()?.let { NetworkCall.Success(it) }!!
            } else{
                _accountDetails. value = response.errorBody()?.let { NetworkCall.Error(it.toString()) }!!
            }
        }
    }
}