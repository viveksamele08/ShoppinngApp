package com.verifone.feature.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verifone.core.domain.model.Product
import com.verifone.core.domain.usecase.GetProductsUseCase
import com.verifone.core.domain.usecase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    init {
        _isLoading.value = true
        loadInitialProducts()
        observeSearchQuery()

    }

    private fun loadInitialProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SearchViewModel", "API CAlled for products ALl Product")

            getProductsUseCase().collect { products ->

                _allProducts.value = products
                _filteredProducts.value = products
                    _isLoading.value = false

            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query -> resolveSearchFlow(query) }
                .flowOn(Dispatchers.IO)
                .collect { products ->
                    _filteredProducts.value = products
                        _isLoading.value = false
                }
        }
    }

    private suspend fun resolveSearchFlow(query: String): Flow<List<Product>> {
        return if (query.isBlank()) {
            flowOf(_allProducts.value)
        } else {
            Log.d("SearchViewModel", "API CAlled for products with query: $query")
            searchProductsUseCase(query)
        }
    }

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
