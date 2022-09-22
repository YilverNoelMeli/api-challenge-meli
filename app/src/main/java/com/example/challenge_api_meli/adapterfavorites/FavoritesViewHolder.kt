package com.example.challenge_api_meli.adapterfavorites

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.interfaces.ClickItem
import com.example.challenge_api_meli.models.ItemsResponse
import com.example.challenge_api_meli.sharedmanager.SharedManager
import com.example.challenge_api_meli.databinding.ItemFavoritesBinding
import com.squareup.picasso.Picasso
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