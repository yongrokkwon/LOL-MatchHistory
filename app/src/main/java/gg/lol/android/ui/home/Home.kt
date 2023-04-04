package gg.lol.android.ui.home

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import gg.lol.android.BuildConfig
import gg.lol.android.R
import gg.lol.android.ui.UiState
import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.ui.navigation.LOLMatchHistoryRoute
import gg.lol.android.ui.theme.BackgroundPrimaryColor
import gg.lol.android.ui.theme.ButtonTextColor
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.LightGray
import gg.lol.android.ui.view.AlertErrorDialog
import gg.lol.android.ui.view.IconFavorite
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.ui.view.OnLifecycleEvent
import gg.lol.android.util.TierExtensions.toDrawable
import gg.op.lol.domain.models.SearchHistorySummonerJoin

@Composable
fun HomeView(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val context = LocalContext.current as Activity
    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Loading -> LoadingView()
        is UiState.Error -> AlertErrorDialog(throwable = state.error) { context.finish() }
        is UiState.Success -> HomeView(
            navController,
            viewModel,
            state.data,
            viewModel.latestVersion
        )
    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> viewModel.getFavorites()
            else -> Unit
        }
    }
}

@Composable
fun HomeView(
    navController: NavController,
    viewModel: MainViewModel,
    data: List<SearchHistorySummonerJoin>,
    latestVersion: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp).height(40.dp)
                .clickable { navController.navigate(LOLMatchHistoryRoute.Search.route) },
            painter = painterResource(id = R.drawable.home_search),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        FavoriteSummonerView(navController, viewModel, data, latestVersion)
        CreateEmptySummoner()
    }
}

@Composable
fun CreateFavoriteSummonerView() {
}

@Composable
fun FavoriteSummonerView(
    navController: NavController,
    viewModel: MainViewModel,
    data: List<SearchHistorySummonerJoin>,
    latestVersion: String
) {
    Column(
        modifier = Modifier.padding(top = 8.dp).background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.home_favorite_summoner),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.home_change_index),
                    fontWeight = FontWeight.Normal,
                    style = GUIDE_STYLE
                )
                Icon(
                    modifier = Modifier.width(15.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        if (data.isEmpty()) {
            EmptyFavoriteSummonerView(
                modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
                navController
            )
        } else {
            LazyRow {
                items(data) { item ->
                    Box {
                        FavoriteSummonerItemView(
                            Modifier.padding(start = 8.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = LightGray)
                                .clickable {
                                    navController.navigate(
                                        LOLMatchHistoryRoute.MatchHistory.createRoute(
                                            item.summonerName
                                        )
                                    )
                                }
                                .padding(top = 8.dp, bottom = 8.dp)
                                .align(Alignment.Center)
                                .width(IntrinsicSize.Max),
                            item,
                            latestVersion
                        )
                        IconFavorite(
                            Modifier.align(Alignment.TopEnd)
                                .size(25.dp)
                                .padding(end = 4.dp)
                                .clickable {
                                    viewModel.updateFavoriteSummoner(
                                        item.copy(isFavorite = !item.isFavorite)
                                    )
                                },
                            item.isFavorite
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteSummonerItemView(
    modifier: Modifier = Modifier,
    item: SearchHistorySummonerJoin,
    latestVersion: String
) {
    Column(modifier = modifier) {
        FavoriteProfile(
            modifier = Modifier.wrapContentSize()
                .padding(start = 24.dp, end = 24.dp),
            latestVersion,
            item.summonerLevel,
            item.profileIconId
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp),
            text = item.summonerName,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = item.tier.toDrawable()),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = item.tier.toSummaryName(),
                style = TextStyle(fontSize = 11.sp, textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable
fun EmptyFavoriteSummonerView(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    Row(
        modifier = modifier
    ) {
        IconFavorite(isFavorite = true)
        Text(
            modifier = Modifier.padding(),
            text = stringResource(id = R.string.home_favorite_empty_guide_01),
            fontSize = 12.sp
        )
    }
    Text(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        text = stringResource(id = R.string.home_favorite_empty_guide_02),
        textAlign = TextAlign.Center,
        fontSize = 12.sp
    )
    HomeButton(
        stringResource(id = R.string.home_favorite_button),
        onClick = { navController.navigate(LOLMatchHistoryRoute.Search.route) }
    )
}

@Composable
fun CreateEmptySummoner() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp).background(color = Color.White)
            .padding(start = 8.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(color = LightGray)
                    .padding(start = 32.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)).size(60.dp),
                    painter = painterResource(id = R.drawable.home_summoner_empty),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.home_summoner_empty_guide_01),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Text(
            text = stringResource(id = R.string.home_summoner_empty_guide_02),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp).fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        HomeButton(stringResource(id = R.string.home_summoner_button), {
            // TODO
        })
    }
}

@Composable
fun HomeButton(
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = BackgroundPrimaryColor,
    textColor: Color = ButtonTextColor,
    fontSize: TextUnit = 11.sp
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(30.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        )
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize
        )
    }
}

@Composable
fun FavoriteProfile(
    modifier: Modifier = Modifier,
    latestVersion: String,
    summonerLevel: Int,
    profileIconId: Int
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .size(60.dp)
                .padding(0.dp),
            painter = rememberAsyncImagePainter(
                BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                    "/img/profileicon/" + profileIconId + ".png"
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Text(
            text = "$summonerLevel",
            style = TextStyle(color = Color.White, fontSize = 12.sp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .offset(y = 4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.Gray)
                .padding(start = 4.dp, end = 4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
}
