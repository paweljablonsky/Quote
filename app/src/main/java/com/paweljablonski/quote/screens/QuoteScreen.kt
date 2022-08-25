package com.paweljablonski.quote.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paweljablonski.quote.components.Quote
import com.paweljablonski.quote.components.Title
import com.paweljablonski.quote.model.Quote


@Composable
fun QuoteScreen(viewModel: QuoteScreenViewModel = hiltViewModel()
){
    var quote by remember{ mutableStateOf(viewModel.quote.value?.data)}

    viewModel.quote.observeForever {
        quote = it.data
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(16.dp)

    ) {

        quote?.let { Title(it) }
        Spacer(modifier = Modifier.padding(top = 40.dp))
        quote?.let { Quote(it) }
        Spacer(modifier = Modifier.padding(top = 40.dp))

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
            onClick = {
            viewModel.fetchQuote()
        }) {
            Text(text = "Get Quote")
        }
    }
}

