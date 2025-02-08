package com.example.ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ktor.core.presentation.HomeScreen
import com.example.ktor.ui.theme.KtorTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                false
            }
        }
        enableEdgeToEdge()
        setContent {
            KtorTheme {
                HomeScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtorTheme {

    }
}