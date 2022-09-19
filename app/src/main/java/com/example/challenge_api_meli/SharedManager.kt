package com.example.challenge_api_meli

import android.content.Context

class SharedManager {


    fun saveLikeFavorite(idItem: String, context: Context) {
        val sharedPreference = context.getSharedPreferences("favorites_preferences", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var listFavoriteId: MutableList<String> = mutableListOf()
        var favoritePreferences = sharedPreference.getString("favoriteItems", "")

        if (favoritePreferences.isNullOrEmpty()) {
            listFavoriteId.add(idItem)
            editor.putString("favoriteItems", listFavoriteId.toString())
            editor.commit()
        } else {
            var arrayWithoutCharacters = favoritePreferences.replace("[", "").replace("]", "").replace(" ", "")
            listFavoriteId = mutableListOf(*arrayWithoutCharacters.split(",").toTypedArray())
            listFavoriteId.remove("")
            listFavoriteId.add(idItem)
            editor.putString("favoriteItems", listFavoriteId.toString())
            editor.commit()
        }
    }

    fun deleteOfFavorites(id :String, context: Context){
        var listFavoriteId: MutableList<String> = mutableListOf()
        val sharedPreference = context.getSharedPreferences("favorites_preferences", Context.MODE_PRIVATE)
        var favoritePreferences = sharedPreference.getString("favoriteItems", "")
        var arrayWithoutCharacters = favoritePreferences?.replace("[", "")?.replace("]", "")?.replace(" ","")
        var editor = sharedPreference.edit()
        arrayWithoutCharacters?.let {
            listFavoriteId = mutableListOf(*arrayWithoutCharacters.split(",").toTypedArray())
        }
        listFavoriteId.remove(id)
        editor.putString("favoriteItems", listFavoriteId.toString())
        editor.commit()
    }
}