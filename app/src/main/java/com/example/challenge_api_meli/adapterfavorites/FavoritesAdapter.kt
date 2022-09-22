package com.example.challenge_api_meli.adapterfavorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.interfaces.ClickItem
import com.example.challenge_api_meli.models.ItemsResponse
import com.example.challenge_api_meli.R

class FavoritesAdapter(private val listFavorites: List<ItemsResponse>, val call: ClickItem): RecyclerView.Adapter<FavoritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder((layoutInflater.inflate(R.layout.item_favorites, parent, false)))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val itemPosition = listFavorites[position]
        val context = holder.itemView.context.applicationContext
        holder.OnBind(itemPosition, context, call)
    }

    override fun getItemCount(): Int = listFavorites.size
}