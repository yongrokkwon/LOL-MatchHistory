package gg.lol.android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import gg.lol.android.ui.MainScreen
import gg.lol.android.ui.theme.LOLGGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    MainScreen()
}