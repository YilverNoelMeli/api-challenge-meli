package com.example.challenge_api_meli.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder (view: View):RecyclerView.ViewHolder(view) {
    private var binding = ItemProductBinding.bind(view)
    fun OnBind(itemPosition: ItemsResponse) {
        binding.tvItem.text = itemPosition.body.title
        Picasso
            .get()
            .load(itemPosition.body.secure_thumbnail)
            .into(binding.imPhoto)

    }
}