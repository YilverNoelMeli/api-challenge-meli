package com.example.challenge_api_meli.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.R

class ProductAdapter (private val listProducts: List<ItemsResponse>): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder((layoutInflater.inflate(R.layout.item_product, parent, false)))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val itemPosition = listProducts[position]
        holder.OnBind(itemPosition)
    }

    override fun getItemCount(): Int = listProducts.size
}



