package com.example.challenge_api_meli.Adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_api_meli.SharedManager
import com.example.challenge_api_meli.ItemsResponse
import com.example.challenge_api_meli.ProductDetail
import com.example.challenge_api_meli.databinding.ItemProductBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductViewHolder (view: View):RecyclerView.ViewHolder(view)  {
    private var binding = ItemProductBinding.bind(view)
    fun OnBind(itemPosition: ItemsResponse, holder: ProductViewHolder, isFavorite: Boolean ) {
        val context = holder.itemView.context.applicationContext
        val formartCO = DecimalFormat("#,###")
        var urlImage = ""
        val price = formartCO.format(itemPosition.body.price.toDouble()).toString().replace(",",".")
        val title = itemPosition.body.title
        val idItem = itemPosition.body.id
        ///////////////////////////////////////////////////
        val detailsMethods = SharedManager()
        binding.ivHearFull.visibility = View.GONE
        binding.ivHearEmpty.visibility = View.VISIBLE

        if (isFavorite == true){
            binding.ivHearFull.visibility = View.VISIBLE
            binding.ivHearEmpty.visibility = View.GONE

        }

        binding.ivHearEmpty.setOnClickListener {
            binding.ivHearFull.visibility = View.VISIBLE
            binding.ivHearEmpty.visibility = View.GONE
            idItem?.let { detailsMethods.saveLikeFavorite(idItem, context) }
        }

        binding.ivHearFull.setOnClickListener {
            binding.ivHearFull.visibility = View.GONE
            binding.ivHearEmpty.visibility = View.VISIBLE
            idItem?.let {
                detailsMethods.deleteOfFavorites(idItem, context)
            }
        }
        ////////////////////////////////////////////////////
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
            goProductDetail.putExtra("idItem", idItem)
            context.startActivity(goProductDetail)
        }

    }
}