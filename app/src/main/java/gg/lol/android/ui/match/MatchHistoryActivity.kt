package gg.lol.android.ui.match

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
import gg.lol.android.ui.UiState
import gg.lol.android.ui.account.ROUTE_LOGIN
import gg.lol.android.ui.theme.LOLMatchHistoryTheme
import gg.lol.android.ui.view.IconFavorite
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.ui.view.NetworkError
import gg.op.lol.domain.models.Summoner

@AndroidEntryPoint
class MatchHistoryActivity : ComponentActivity() {

    companion object {
        const val EXTRA_NICKNAME = "EXTRA_NICKNAME"
    }

    private val viewModel: MatchHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNickName(intent.getStringExtra(EXTRA_NICKNAME) ?: "")

        setContent {
            LOLMatchHistoryTheme {
                MatchHistoryRoot()
            }
        }
    }
}

@Composable
fun MatchHistoryRoot(viewModel: MatchHistoryViewModel = hiltViewModel()) {
    val context = LocalContext.current as Activity

    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Success -> {
            MatchHistoryScreen(viewModel, state.data)
        }
        is UiState.Error -> NetworkError(modifier = Modifier.clickable { context.finish() })
        is UiState.Loading -> LoadingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchHistoryScreen(viewModel: MatchHistoryViewModel = hiltViewModel(), data: Summoner) {
    val context = LocalContext.current as Activity
    val navController = rememberNavController()
    val appBarBackground = viewModel.appbarBackground.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        MatchHistoryNavHost(viewModel, navController)
        CenterAlignedTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = appBarBackground),
            title = {
                Text(
                    text = data.summonerName,
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
                        if (viewModel.screenCloseCheck.value) {
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
                    if (viewModel.screenCloseCheck.value) {
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
fun MatchHistoryNavHost(
    viewModel: MatchHistoryViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(modifier = modifier, navController = navController, startDestination = ROUTE_LOGIN) {
        composable(route = ROUTE_LOGIN) {
            viewModel.setScreenCloseCheck(false)
            MatchHistoryView(viewModel, navController)
        }
    }
}
