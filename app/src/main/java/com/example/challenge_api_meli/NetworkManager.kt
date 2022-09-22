package com.example.challenge_api_meli

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.getSystemService

class NetworkManager {
    fun isConected(context: Context): Boolean {
        val connectionManger: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManger.activeNetworkInfo
        val isConnected: Boolean? = activeNetwork?.isConnectedOrConnecting == true

        return isConnected == true


    }

}