package com.example.challenge_api_meli.APIService

import com.example.challenge_api_meli.*
import com.example.challenge_api_meli.Utils.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @Headers("Authorization: Bearer ${API_KEY}")
    @GET("sites/MCO/domain_discovery/search")
    suspend fun getCategoryId(
        @Query("limit") Limit: Int = 1,
        @Query("q") categoruName: String
    ): Response<List<Category>>

    @Headers("Authorization: Bearer ${API_KEY}")
    @GET("/highlights/MCO/category/{section_id}")
    suspend fun getHighlightList(@Path("section_id") section: String): Response <HighLightList>


    @Headers("Authorization: Bearer ${API_KEY}")
    @GET("/items")
    suspend fun getMultiGet(@Query("ids") listItems: String): Response<MutableList<ItemsResponse>>

}