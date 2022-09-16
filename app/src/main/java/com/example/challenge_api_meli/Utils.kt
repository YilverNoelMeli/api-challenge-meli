package com.example.challenge_api_meli

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object{
        const val API_KEY = "APP_USR-3344393545027119-091523-002580571428b399a3a5533b6f5853e5-1131425556"

       fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}