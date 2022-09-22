package com.example.challenge_api_meli

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challenge_api_meli.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    val connection = NetworkManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (connection.isConected(this)) {
            binding.layoutE.layoutE.visibility = View.GONE
            val title = intent.getStringExtra("title")
            val imageUrl = intent.getStringExtra("image")
            val price = intent.getStringExtra("price").toString().replace(",", ".")
            val idItem = intent.getStringExtra("idItem")
            val sharedPreference =
                getSharedPreferences("favorites_preferences", Context.MODE_PRIVATE)
            var favoritePreferences = sharedPreference.getString("favoriteItems", "")
            var isFavorite = favoritePreferences?.contains(idItem.toString())
            val btnFavoriteHeader = binding.ivFavoriteHead
            val btnFavoritesHeaderSelect = binding.ivFavoriteSelected
            val btnFavoriteFooter = binding.ivFavoriteFoot
            val btnFavoriteFootSelect = binding.ivFavoriteFootSelected
            val sharedManager = SharedManager()
            if (isFavorite == true) {
                favoritesOptions(false)
            }
            binding.tvSmallTitle.text = title
            binding.tvMediumTitle.text = title
            binding.tvPriceDetail.text = "$ ${price}"

            imageUrl?.let {
                loadImage(imageUrl)
            }

            btnFavoriteHeader.setOnClickListener {
                favoritesOptions(false)
                idItem?.let {
                    sharedManager.saveLikeFavorite(idItem, this)
                }
            }

            btnFavoritesHeaderSelect.setOnClickListener {
                favoritesOptions(true)
                idItem?.let {
                    sharedManager.deleteOfFavorites(idItem, this)
                }
            }

            btnFavoriteFooter.setOnClickListener {
                favoritesOptions(false)
                idItem?.let {
                    sharedManager.saveLikeFavorite(idItem, this)
                }
            }

            btnFavoriteFootSelect.setOnClickListener {
                favoritesOptions(true)
                idItem?.let {
                    sharedManager.deleteOfFavorites(idItem, this)
                }
            }
        } else {
            val viewErrors = binding.layoutE
            binding.layoutE.layoutE.visibility = View.VISIBLE
            val linearNoFound = viewErrors.llNoFound
            val relativeNoFound = viewErrors.rlNoFound
            val linearConnection = viewErrors.llWithoutConnection
            val relativeConnection = viewErrors.rlWithoutConnection
            linearNoFound.visibility = View.GONE
            relativeNoFound.visibility = View.GONE
            linearConnection.visibility = View.GONE
            relativeConnection.visibility = View.GONE
        }

    }

    private fun favoritesOptions(isSeleted: Boolean) {
        when (isSeleted) {
            true -> {
                binding.ivFavoriteSelected.visibility = View.GONE
                binding.ivFavoriteHead.visibility = View.VISIBLE
                binding.ivFavoriteFoot.visibility = View.VISIBLE
                binding.ivFavoriteFootSelected.visibility = View.GONE
                binding.tvAddFavoriteState.text = "Agregar a favoritos"
            }
            false -> {
                binding.ivFavoriteFoot.visibility = View.GONE
                binding.ivFavoriteFootSelected.visibility = View.VISIBLE
                binding.ivFavoriteSelected.visibility = View.VISIBLE
                binding.ivFavoriteHead.visibility = View.GONE
                binding.tvAddFavoriteState.text = "Quitar de favoritos"
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