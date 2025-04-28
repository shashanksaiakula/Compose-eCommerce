package com.example.ecommersandroid.apiServices

import com.example.ecommersandroid.data.AccoutDetails
import com.example.ecommersandroid.data.SingIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthFlow {

    @POST("v1/accounts:signInWithPassword")
    suspend fun signInWithPassword(
        @Query ("key") key: String,
       @Body body: HashMap<String, String>
    ) : Response<SingIn>

    @POST("v1/accounts:signUp")
    suspend fun Login(
        @Query("key") key :String,
        @Body body: HashMap<String, String>
    ) : Response<SingIn>

    @POST("v1/accounts:update")
    suspend fun updateUserProfile(
        @Query("key") key: String,
        @Body body: HashMap<String, String>
    ) : Response<Any>

    @POST("v1/accounts:sendOobCode")
    suspend fun sendEmailVarify(
        @Query("key") key: String,
        @Body body: HashMap<String, String>
    ) : Response<Any>

    @POST("v1/accounts:lookup")
    suspend fun accoutDetails(
        @Query("key") key: String,
        @Body body: HashMap<String, String>
    ) : Response<AccoutDetails>
}
