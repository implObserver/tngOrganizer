package com.example.tngorganizer.app

import com.example.tngorganizer.navigations.MainNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tngorganizer.app.ui.theme.TngOrganizerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint // 👈 Добавь вот это
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TngOrganizerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainNavGraph() // 👈 Навигация
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TngOrganizerTheme {
        Greeting("Android")
    }
}