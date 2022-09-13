package com.example.challenge_api_meli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge_api_meli.APIService.ProductService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchUrl("libros")
    }

    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchUrl(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ProductService::class.java).getCategoryId(1, query)
            val call2 = getRetrofit().create(ProductService::class.java)
                .getHighlightList(call.get(0).category_id)

            runOnUiThread {
                println((call.get(0).category_id))
                var s = ""
                call2.content.forEach {
                    it.id
                    s += it.id + ","
                }
                println(s)
                obtainMultiget(s)

            }
        }

    }
    private fun obtainMultiget(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call3 = getRetrofit().create(ProductService::class.java).getMultiGet(query)
            runOnUiThread{
                println(call3.forEach{
                    println(it.body.title)
                })

            }
        }

        }
}