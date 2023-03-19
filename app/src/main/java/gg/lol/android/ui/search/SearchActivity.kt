package gg.lol.android.ui.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.ui.theme.LOLGGTheme

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Test
        viewModel.insert(SearchHistory(nickname = "hide on bush"))
        viewModel.insert(SearchHistory(nickname = "hide on bush2"))
        viewModel.insert(SearchHistory(nickname = "hide on bush3"))
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
