package com.verifone.feature.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.verifone.core.ui.components.LoadingIndicator
import com.verifone.core.ui.components.ProductCard
import com.verifone.core.ui.components.SearchBar
import com.verifone.feature.search.viewmodel.SearchViewModel

@Composable
fun ProductSearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val query by viewModel.searchQuery.collectAsState()
    val products by viewModel.filteredProducts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SearchBar(
            value = query,
            onValueChange = { viewModel.onQueryChanged(it) },
            placeholderText = "Search products..."
        )

        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading) {
            LoadingIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ) {
                items(products.size) { index ->
                    ProductCard(product = products[index])
                }
            }
        }
    }
}
