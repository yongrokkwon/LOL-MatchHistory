package gg.lol.android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import gg.lol.android.ui.MainScreen
import gg.lol.android.ui.theme.LOLMatchHistoryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLMatchHistoryTheme {
                MainView(viewModel)
            }
        }
    }
}

@Composable
fun MainView(viewModel: MainViewModel) {
    MainScreen(viewModel)
}
