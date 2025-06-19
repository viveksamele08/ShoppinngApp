package com.verifone.core.network.api

import com.verifone.core.network.dto.ProductDto
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("search")
    suspend fun searchProduct(query:String): List<ProductDto>
}
