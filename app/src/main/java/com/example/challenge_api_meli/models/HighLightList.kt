package com.example.challenge_api_meli.models

data class HighLightList(var content: List<HighLight>) {
    val items: List<HighLight>
        get() = content.filter { itemResponse -> itemResponse.type == "ITEM" }
}

