package com.example.ecommersandroid.utils

import retrofit2.Response

sealed class NetworkCall<out T> {
    data object Loading : NetworkCall<Nothing>()
    data class Success<out T>(val response: T) : NetworkCall<T>()
    data class Error(val error: String) : NetworkCall<Nothing>()
}
