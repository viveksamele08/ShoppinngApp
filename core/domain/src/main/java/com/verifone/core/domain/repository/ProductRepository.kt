package com.verifone.core.domain.repository

import com.verifone.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllProducts(): Flow<List<Product>>
    suspend fun searchProduct(query: String):Flow<List<Product>>
}
