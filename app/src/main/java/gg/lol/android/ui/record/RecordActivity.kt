package gg.lol.android.ui.record

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import gg.lol.android.ui.theme.LOLGGTheme

@AndroidEntryPoint
class RecordActivity : ComponentActivity() {
    private val viewModel: RecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                RecordView(viewModel)
            }
        }
    }
}

@Composable
fun RecordView(viewModel: RecordViewModel) {
    RecordScreen(viewModel)
}