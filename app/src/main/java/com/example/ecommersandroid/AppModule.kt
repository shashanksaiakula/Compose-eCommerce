package com.example.ecommersandroid

import com.example.ecommersandroid.apiServices.AuthFlow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun retrofitProvider() : Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://identitytoolkit.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuth(retrofit: Retrofit) : AuthFlow{
        return retrofit.create(AuthFlow::class.java)
    }

}