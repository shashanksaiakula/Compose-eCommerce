package com.example.ecommersandroid.Repo

import com.example.ecommersandroid.apiServices.AuthFlow
import com.example.ecommersandroid.data.AccoutDetails
import com.example.ecommersandroid.data.SingIn
import retrofit2.Response
import javax.inject.Inject

class AuthFlowImp @Inject constructor(private val authFlow : AuthFlow) {

    suspend fun SingIn(body : HashMap<String, String>, key : String): Response<SingIn>{
        return authFlow.signInWithPassword(key,body)
    }

    suspend fun SingUp(key : String, body : HashMap<String, String>) : Response<SingIn> {
        return authFlow.Login(key,body)
    }
    suspend fun updateUserProfile(key: String, body: HashMap<String, String>) : Response<Any>{
        return authFlow.updateUserProfile(key,body)
    }
    suspend fun sendEmailVerfit(key: String,body: HashMap<String, String>) : Response<Any>{
        return authFlow.sendEmailVarify(key,body)
    }
    suspend fun accoutDetails(key: String,body: HashMap<String, String>) : Response<AccoutDetails>{
        return authFlow.accoutDetails(key,body)
    }
}