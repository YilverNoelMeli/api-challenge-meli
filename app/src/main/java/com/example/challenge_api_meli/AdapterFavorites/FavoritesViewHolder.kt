package com.example.challenge_api_meli.AdapterFavorites

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.Adapter.ProductViewHolder
import com.example.challenge_api_meli.Interfaces.ClickItem
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.SharedManager
import com.example.challenge_api_meli.databinding.ItemFavoritesBinding
import com.example.challenge_api_meli.databinding.ItemProductBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import java.text.DecimalFormat

class FavoritesViewHolder (view: View): RecyclerView.ViewHolder(view){
    private var binding = ItemFavoritesBinding.bind(view)
    fun OnBind(itemPosition: ItemsResponse, context: Context, call: ClickItem) {
        val formartCO = DecimalFormat("#,###")
        val price = formartCO.format(itemPosition.body.price.toDouble()).toString().replace(",",".")
        val sharedManager = SharedManager()
        binding.tvTitleFav.text = itemPosition.body.title
        binding.tvPriceFav.text = "$ ${price}"
        var urlImage = ""
        itemPosition.body.pictures.forEach {
            urlImage = it.secure_url
        }
        Picasso
            .get()
            .load(urlImage)
            .into(binding.ivPhotoFav)

        binding.tvDeleteFav.setOnClickListener {
            sharedManager.deleteOfFavorites(itemPosition.body.id, context)
            call.clickItem(adapterPosition)
        }


    }
}