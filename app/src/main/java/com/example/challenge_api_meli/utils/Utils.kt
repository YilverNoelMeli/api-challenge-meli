package com.example.challenge_api_meli.utils

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object{
        const val API_KEY = "APP_USR-3344393545027119-092209-4248f9833d2a0b42597f2c78e9ebf095-1131425556"
        const val PREDITOR_ERORR = "preditor"
        const val MULTIGET_ERORR = "multiget"
        const val CONNECTION_ERROR = "noConnected"
       fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}