package gg.lol.android.ui.record

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gg.lol.android.ui.account.ROUTE_LOGIN
import gg.lol.android.ui.theme.LOLGGTheme
import gg.lol.android.ui.view.IconFavorite
import gg.lol.android.ui.view.LoadingView
import gg.op.lol.domain.models.Summoner
import gg.op.lol.presentation.UiState
import gg.op.lol.presentation.viewmodel.RecordViewModel

@AndroidEntryPoint
class RecordActivity : ComponentActivity() {

    companion object {
        const val EXTRA_NICKNAME = "EXTRA_NICKNAME"
    }

    private val viewModel: RecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNickName(intent.getStringExtra(EXTRA_NICKNAME) ?: "")
        setContent {
            LOLGGTheme {
                RecordView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordView(viewModel: RecordViewModel = hiltViewModel()) {
    when (val state = viewModel.headerUiState.collectAsState().value) {
        is UiState.Success -> {
            RecordView(viewModel, state.data)
        }
        is UiState.Error -> {
        }
        is UiState.Loading -> LoadingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordView(viewModel: RecordViewModel = hiltViewModel(), data: Summoner) {
    val context = LocalContext.current as Activity
    val navController = rememberNavController()
    val nickName by viewModel.nickName.observeAsState(initial = "")
    val appBarBackground by viewModel.appbarBackground.observeAsState(initial = Color.Transparent)

    Box(modifier = Modifier.fillMaxSize()) {
        RecordNavHost(viewModel, navController)
        CenterAlignedTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = appBarBackground),
            title = {
                Text(
                    text = nickName,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (viewModel.screenCloseCheck) {
                            context.finish()
                        } else {
                            if (navController.backQueue.size == 2) {
                                context.finish()
                            } else {
                                navController.popBackStack()
                            }
                        }
                        viewModel.setScreenCloseCheck(false)
                    }
                ) {
                    if (viewModel.screenCloseCheck) {
                        Icon(Icons.Filled.Close, null)
                    } else {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            },
            actions = {
                IconFavorite(
                    modifier = Modifier
                        .clickable { /* TODO */ },
                    isFavorite = false // TODO
                )
            }
        )
    }
}

@Composable
fun RecordNavHost(
    viewModel: RecordViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(modifier = modifier, navController = navController, startDestination = ROUTE_LOGIN) {
        composable(route = ROUTE_LOGIN) {
            viewModel.setScreenCloseCheck(false)
            RecordListScreen(viewModel, navController)
        }
    }
}
