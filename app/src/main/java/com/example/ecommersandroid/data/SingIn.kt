package com.example.ecommersandroid.data

import java.io.Serializable

data class SingIn(
    val email: String,
    val displayName : String,
    val idToken : String,
    val refreshToken : String,
    val localId : String,
    val expiresIn : String,
    val registered : Boolean
) : Serializable
