package com.paweljablonski.quote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.paweljablonski.quote.model.Quote
import com.paweljablonski.quote.screens.QuoteScreenViewModel


@Composable
fun Title(quote: Quote){
    Text(
        text = "Quote by ${quote.originator.name}",
        color = Color.White
    )
}
@Composable
fun Quote(
    quote: Quote
){
    Text(
        text = quote.content,
        color = Color.White
    )
}
