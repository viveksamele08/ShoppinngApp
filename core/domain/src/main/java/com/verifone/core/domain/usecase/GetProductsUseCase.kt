package com.verifone.core.domain.usecase

import com.verifone.core.domain.model.Product
import com.verifone.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()
}
