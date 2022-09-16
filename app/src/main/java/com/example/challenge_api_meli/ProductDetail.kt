package com.example.challenge_api_meli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge_api_meli.APIService.ProductService
import com.example.challenge_api_meli.Utils.Companion.getRetrofit
import com.example.challenge_api_meli.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("image")
        val price = intent.getStringExtra("price")
        binding.tvSmallTitle.text = title
        binding.tvMediumTitle.text = title
        binding.tvPriceDetail.text = "$ ${price}"
        imageUrl?.let {
            loadImage(imageUrl)
        }
    }

    private fun loadImage(url: String) {
        Picasso
            .get()
            .load(url)
            .into(binding.ivProduct)
    }

}