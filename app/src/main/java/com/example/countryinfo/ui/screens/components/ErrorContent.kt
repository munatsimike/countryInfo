package com.example.countryinfo.ui.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorContent(errorMsg: String){
    Text(text = errorMsg)
}