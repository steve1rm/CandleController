package me.androidbox.cakelightingcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val candleViewModel by viewModels<CandleViewModel>()
        setContent {
            App(
                candleViewModel = candleViewModel
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        candleViewModel = CandleViewModel()
    )
}
