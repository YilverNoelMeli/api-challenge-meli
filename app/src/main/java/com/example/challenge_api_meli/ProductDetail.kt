package com.example.challenge_api_meli

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.challenge_api_meli.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("image")
        val price = intent.getStringExtra("price").toString().replace(",",".")
        val idItem = intent.getStringExtra("idItem")
        val sharedPreference = getSharedPreferences("favorites_preferences", Context.MODE_PRIVATE)
        var favoritePreferences = sharedPreference.getString("favoriteItems", "")
        var isFavorite = favoritePreferences?.contains(idItem.toString())
        if (isFavorite == true){
            binding.ivFavoriteSelected.visibility = View.VISIBLE
            binding.ivFavoriteHead.visibility = View.GONE
        }
        binding.tvSmallTitle.text = title
        binding.tvMediumTitle.text = title
        binding.tvPriceDetail.text = "$ ${price}"
        imageUrl?.let {
            loadImage(imageUrl)
        }

        val btnFavoriteHeader = binding.ivFavoriteHead
        val sharedManager = SharedManager()
        btnFavoriteHeader.setOnClickListener {
            idItem?.let {
                binding.ivFavoriteSelected.visibility = View.VISIBLE
                binding.ivFavoriteHead.visibility = View.GONE
                sharedManager.saveLikeFavorite(idItem, this)
            }
        }

        binding.ivFavoriteSelected.setOnClickListener {
            binding.ivFavoriteSelected.visibility = View.GONE
            binding.ivFavoriteHead.visibility = View.VISIBLE
            idItem?.let {
                sharedManager.deleteOfFavorites(idItem, this)
            }
        }
    }

    private fun loadImage(url: String) {
        Picasso
            .get()
            .load(url)
            .into(binding.ivProduct)
    }

}