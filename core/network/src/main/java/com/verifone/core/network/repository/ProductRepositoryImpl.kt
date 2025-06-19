package com.verifone.core.network.repository

import com.verifone.core.domain.model.Product
import com.verifone.core.domain.repository.ProductRepository
import com.verifone.core.network.api.ProductApi
import com.verifone.core.network.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun searchProduct(query: String): Flow<List<Product>> = kotlinx.coroutines.flow.flow {
        val products = api.getProducts()
            .filter { it.title.contains(query, ignoreCase = true) }
            .map { it.toDomain() }
        emit(products)
    }


    override suspend fun getAllProducts(): Flow<List<Product>> = kotlinx.coroutines.flow.flow {
        val products = api.getProducts().map { it.toDomain() }
        emit(products)
    }
}
