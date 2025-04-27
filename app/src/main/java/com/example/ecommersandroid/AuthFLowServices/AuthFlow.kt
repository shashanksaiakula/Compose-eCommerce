package com.example.ecommersandroid.AuthFLowServices

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

}
