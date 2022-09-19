package com.example.challenge_api_meli.Adapter

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.ProductDetail
import com.example.challenge_api_meli.R
import kotlinx.coroutines.withContext

class ProductAdapter (private val listProducts: List<ItemsResponse>): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder((layoutInflater.inflate(R.layout.item_product, parent, false)))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val itemPosition = listProducts[position]
        val context = holder.itemView.context.applicationContext
        val sharedPreference = context.getSharedPreferences("favorites_preferences", Context.MODE_PRIVATE)
        var favoritePreferences = sharedPreference.getString("favoriteItems", "")
        val isFavorite = favoritePreferences?.contains(itemPosition.body.id) ?:false

        holder.OnBind(itemPosition, holder, isFavorite)
    }

    override fun getItemCount(): Int = listProducts.size
}



