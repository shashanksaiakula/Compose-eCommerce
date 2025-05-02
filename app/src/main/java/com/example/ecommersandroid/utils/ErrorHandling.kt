package com.example.ecommersandroid.utils

import org.json.JSONObject

class ErrorHandling  {
    companion object{
        fun getErrorMessage(error: String): Error {
            if(error.isNullOrEmpty()) return Error("Something went wrong")
            try {
                val jsonObj = JSONObject(error)
//               return Error(jsonObj.getString("error"))
                return  Error(jsonObj.getJSONObject("error").getString("message"))
            } catch (e: Exception) {
                return Error("Something went wrong")
            }
            return Error("Something went wrong")
        }

    }
}