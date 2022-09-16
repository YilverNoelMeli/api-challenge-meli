package com.example.challenge_api_meli.Adapter

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.ProductDetail
import com.example.challenge_api_meli.databinding.ItemProductBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductViewHolder (view: View):RecyclerView.ViewHolder(view) {
    private var binding = ItemProductBinding.bind(view)
    fun OnBind(itemPosition: ItemsResponse, holder: ProductViewHolder ) {
        val context = holder.itemView.context.applicationContext
        val formartCO = DecimalFormat("#,###")
        var urlImage = ""
        val price = formartCO.format(itemPosition.body.price.toDouble())
        val title = itemPosition.body.title
        binding.tvItem.text = title
        binding.tvPrice.text = "$ ${price}"

        itemPosition.body.pictures.forEach {
            urlImage = it.secure_url
        }

        Picasso
            .get()
            .load(urlImage)
            .into(binding.imPhoto)

        holder.itemView.setOnClickListener {
            val goProductDetail = Intent(context, ProductDetail::class.java)
            goProductDetail.putExtra("title", title)
            goProductDetail.putExtra("price", price)
            goProductDetail.putExtra("image", urlImage)
            context.startActivity(goProductDetail)
        }

    }
}