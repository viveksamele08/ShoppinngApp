package com.verifone.core.domain.usecase

import com.verifone.core.domain.model.Product
import com.verifone.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Flow<List<Product>> {
        return repository.searchProduct(query)
    }
}
