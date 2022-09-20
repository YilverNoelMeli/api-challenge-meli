package com.example.challenge_api_meli

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object{
        const val API_KEY = "APP_USR-3344393545027119-092013-1afd535b2f328f72682463e05698a657-1131425556"

       fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}