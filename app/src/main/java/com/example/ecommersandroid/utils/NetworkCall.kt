package com.example.ecommersandroid.utils

sealed class NetworkCall<out T> {
    data object Loading : NetworkCall<Nothing>()
    data class Success<out T>(val response: T) : NetworkCall<T>()
    data class Error(val error: kotlin.Error?) : NetworkCall<Nothing>()
}
