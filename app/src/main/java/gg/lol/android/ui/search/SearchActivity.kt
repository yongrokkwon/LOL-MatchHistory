package gg.lol.android.ui.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import gg.lol.android.ui.theme.LOLGGTheme

class SearchActivity : ComponentActivity() {
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                SearchView(viewModel)
            }
        }
    }
}

@Composable
fun SearchView(viewModel: SearchViewModel) {
    SearchScreen(viewModel)
}