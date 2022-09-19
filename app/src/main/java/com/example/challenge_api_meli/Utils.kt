package com.example.challenge_api_meli

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object{
        const val API_KEY = "APP_USR-3344393545027119-091818-f6e09f721bf327ac200ba64aa64506eb-1131425556"

       fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}