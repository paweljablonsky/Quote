package com.paweljablonski.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paweljablonski.quote.network.ConnectivityObserver
import com.paweljablonski.quote.network.NetworkConnectivityObserver
import com.paweljablonski.quote.repository.QuoteRepository
import com.paweljablonski.quote.screens.QuoteScreen

import com.paweljablonski.quote.screens.QuoteScreenViewModel
import com.paweljablonski.quote.ui.theme.QuoteTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewModelComponentBuilder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            QuoteTheme {

                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.onBackground
                    ) {
                        if (status == ConnectivityObserver.Status.Available){
                            QuoteScreen()
                        }else{
                            Text(text = "Network status: $status", color = Color.White, textAlign = TextAlign.Center)
                        }
                    }
            }
        }
    }
}

