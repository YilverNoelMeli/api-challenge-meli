package com.example.challenge_api_meli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_api_meli.APIService.ProductService
import com.example.challenge_api_meli.Adapter.ProductAdapter
import com.example.challenge_api_meli.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnSearch = binding.btnSearch
        val etSearch = binding.etSeach
        btnSearch.setOnClickListener {
            searchUrl(etSearch.text.toString())
        }

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
            val responseCall = call.body()
                if (call.isSuccessful){
                    val ls : List<Category>? = responseCall
                    ls?.forEach { requestTop20(it.category_id) }

                }

        }

    }
    private fun requestTop20(value : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call2 = getRetrofit().create(ProductService::class.java)
                    .getHighlightList(value)
            val responseCall2 = call2.body()
             if(call2.isSuccessful){
                 val  highLight  = responseCall2?.items;
                 var s = ""
                 highLight?.forEach {
                     it.id
                     s += it.id + ","
                 }
                 obtainMultiget(s)
             }


        }

    }
    private fun obtainMultiget(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call3 = getRetrofit().create(ProductService::class.java).getMultiGet(query)
            val responseCall3 = call3.body()
            runOnUiThread{
                    if (call3.isSuccessful) {
                        val listProduct : List<ItemsResponse>? = responseCall3
                        listProduct?.let {
                            adapter = ProductAdapter(listProduct)
                            binding.rvRecicler.layoutManager = LinearLayoutManager(baseContext)
                            binding.rvRecicler.adapter = adapter
                        }
                    }

            }
        }

        }
}