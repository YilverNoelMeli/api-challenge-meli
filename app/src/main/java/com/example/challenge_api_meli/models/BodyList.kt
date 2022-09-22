package com.example.challenge_api_meli.models


data class BodyList(val id: String,
                    val title: String,
                    val secure_thumbnail:String,
                    val price: String,
                    val pictures : List<PicturesData>,
                    val plain_text : String)
