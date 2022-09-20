package com.example.challenge_api_meli.AdapterFavorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.Interfaces.ClickItem
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.R
import kotlinx.coroutines.CoroutineScope

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