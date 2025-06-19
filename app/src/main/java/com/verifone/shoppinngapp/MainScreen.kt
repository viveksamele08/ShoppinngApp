package com.verifone.shoppinngapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.verifone.feature.search.ui.ProductSearchScreen
import com.verifone.shoppinngapp.ui.theme.ShoppinngAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    ShoppinngAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text("Shopping App") })
            }
        )
        { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
            ProductSearchScreen()
        }
        }
    }
}