package com.example.challenge_api_meli

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object{
        const val API_KEY = "APP_USR-3344393545027119-092117-4cd43df0df15de729bce1bd9dca3f29b-1131425556"
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