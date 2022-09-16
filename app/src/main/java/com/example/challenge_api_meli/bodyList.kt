package com.example.challenge_api_meli


data class bodyList(val id: String,
                    val title: String,
                    val secure_thumbnail:String,
                    val price: String,
                    val pictures : List<PicturesData>)
