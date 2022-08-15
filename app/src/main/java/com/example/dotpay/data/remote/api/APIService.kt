package com.example.dotpay.data.remote.api

import com.example.dotpay.data.remote.makeupdto.MakeupDtoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/api/v1/products.json")
    suspend fun getMakeUpItems(
        @Query("brand") brandName: String,
        @Query("product_type") product_type: String
    ): ArrayList<MakeupDtoItem>

    companion object {
        const val BASE_URL = "http://makeup-api.herokuapp.com"
    }
}
