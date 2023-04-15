package gg.lol.android.ui.search

import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import gg.lol.android.BuildConfig
import gg.lol.android.R
import gg.lol.android.ui.UiState
import gg.lol.android.ui.navigation.LOLMatchHistoryRoute
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.SearchHint
import gg.lol.android.ui.theme.Typography
import gg.lol.android.ui.view.IconFavorite
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.ui.view.OnLifecycleEvent
import gg.lol.android.util.Extensions.toDrawable
import gg.op.lol.domain.models.SearchHistorySummonerJoin

const val MAX_SEARCH_SIZE = 16

@Composable
fun SearchView(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val latestVersion = viewModel.latestVersion.collectAsState().value
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is UiState.Success -> SearchView(navController, viewModel, uiState.data, latestVersion)
        is UiState.Loading -> LoadingView()
        is UiState.Error -> SearchHistoryErrorView()
    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> viewModel.loadSearchHistories()
            else -> Unit
        }
    }
}

@Composable
fun SearchHistoryErrorView() {
    Text("SearchHistoryErrorView Load Error")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    data: List<SearchHistorySummonerJoin>,
    latestVersion: String
) {
    val summonerName = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
            BasicTextField(
                value = summonerName.value,
                onValueChange = { if (it.length < MAX_SEARCH_SIZE) summonerName.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .onKeyEvent {
                        summonerName.value = summonerName.value.replace("\n", "")
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            navController.navigate(
                                LOLMatchHistoryRoute.MatchHistory.createRoute(
                                    summonerName.value
                                )
                            )
                            return@onKeyEvent true
                        }
                        return@onKeyEvent false
                    }
                    .focusRequester(focusRequester),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        navController.navigate(
                            LOLMatchHistoryRoute.MatchHistory.createRoute(
                                summonerName.value
                            )
                        )
                    },
                    onDone = {
                    }
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 8.dp, end = 0.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                        Box(
                            Modifier
                                .weight(1f)
                                .padding(start = 4.dp)
                        ) {
                            if (summonerName.value.isEmpty()) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.search_hint),
                                    style = SearchHint
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxSize()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.search_recent),
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.clickable { viewModel.deleteAndReloadHistory(data) },
                    text = stringResource(id = R.string.search_all_delete),
                    style = GUIDE_STYLE,
                    textAlign = TextAlign.End
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(data.sortedByDescending { it.lastSearchedAt }) {
                    SearchHistoryItemView(navController, viewModel, it, latestVersion)
                }
            }
        }
    }
}

@Composable
fun SearchHistoryItemView(
    navController: NavController,
    viewModel: SearchViewModel,
    item: SearchHistorySummonerJoin,
    latestVersion: String
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    navController.navigate(
                        LOLMatchHistoryRoute.MatchHistory.createRoute(
                            item.summonerName
                        )
                    )
                }
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(end = 0.dp)
                    .size(50.dp),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/profileicon/" + item.profileIconId + ".png"
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.summonerName, fontWeight = FontWeight.Bold)
                Row {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = item.tier.toDrawable()),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Text(text = item.tier.toSummaryName(), style = TextStyle(fontSize = 15.sp))
                }
            }
        }
        Box(
            modifier = Modifier.align(Alignment.CenterVertically),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row {
                IconFavorite(
                    modifier = Modifier.clickable {
                        viewModel.updateFavoriteSummoner(item.copy(isFavorite = !item.isFavorite))
                    },
                    isFavorite = item.isFavorite
                )
                Icon(
                    modifier = Modifier.clickable {
                        viewModel.deleteAndReloadHistory(listOf(item))
                    },
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
//    SearchScreen()
}
