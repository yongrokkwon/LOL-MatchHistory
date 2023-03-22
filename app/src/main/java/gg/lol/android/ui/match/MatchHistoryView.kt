package gg.lol.android.ui.match

import android.app.Activity
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import gg.lol.android.R
import gg.lol.android.ui.theme.BackgroundPrimaryColor
import gg.lol.android.ui.theme.ButtonTextColor
import gg.lol.android.ui.theme.LightGray
import gg.lol.android.ui.theme.MultiKillBackgroundColor
import gg.lol.android.ui.theme.PrimaryColor
import gg.lol.android.ui.theme.SeasonInformationTextColor
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.ui.view.NetworkError
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Summoner
import gg.op.lol.presentation.UiState
import gg.op.lol.presentation.viewmodel.MatchHistoryViewModel

enum class QueueType { RANKED_SOLO_5X5, RANKED_FLEX_SR }

enum class Tier { CHALLENGER, GRANDMASTER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, IRON }

@Composable
fun MatchHistoryView(
    viewModel: MatchHistoryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current as Activity

    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Success -> {
            MatchHistoryList(viewModel, state.data)
        }
        is UiState.Error -> NetworkError(modifier = Modifier.clickable { context.finish() })
        is UiState.Loading -> LoadingView()
    }
}

@Composable
fun MatchHistoryList(viewModel: MatchHistoryViewModel, summoner: Summoner) {
    val matchHistories = viewModel.matchHistories.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Header(viewModel, summoner)
        MatchHistoryUpdateAndInGame(matchHistories, viewModel, summoner.puuid)
//        SeasonInformation()
        TierInformation(summoner.histories)
        if (matchHistories.itemCount != 0) {
            LazyColumn(
                modifier = Modifier
            ) {
                items(
                    items = matchHistories,
                    key = { matchHistory -> matchHistory.metadata.matchId }
                ) { matchHistory ->
                    matchHistory ?: return@items
                    MatchHistoryCard(matchHistory)
                }
            }
        } else {
            Text(text = "No items to display")
        }
    }
}

@Composable
fun Header(viewModel: MatchHistoryViewModel, summoner: Summoner) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(start = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomStart)
        ) {
            Box(modifier = Modifier.align(Alignment.Bottom)) {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(35.dp))
                        .width(80.dp)
                        .height(80.dp),
                    painter = painterResource(R.drawable.summoner_icon_test),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 2.dp, end = 2.dp)
                        .background(color = Color.Gray),
                    text = "${summoner.summonerLevel}",
                    style = TextStyle(color = Color.White)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = summoner.summonerName,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
//                Text(
//                    modifier = Modifier, text = "T1[Faker]", style = TextStyle(
//                        color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
//                    )
//                )
//                Text(
//                    modifier = Modifier, text = "래더 랭킹 514위", style = TextStyle(
//                        color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
//                    )
//                )
            }
        }
    }
}

@Composable
fun MatchHistoryUpdateAndInGame(
    matchHistories: LazyPagingItems<MatchHistory>,
    viewModel: MatchHistoryViewModel,
    puuid: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.getMatchHistories(puuid) }
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            shape = RoundedCornerShape(10),
            content = {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.match_update),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                )
            },
            onClick = { /* TODO */ }
        )
    }
}

@Composable
fun SeasonInformation() {
    // TODO
    val items = listOf(mapOf(Pair("S2022", "DIAMOND 1"), Pair("S2021", "DIAMOND 2")))
    LazyRow(modifier = Modifier) {
        itemsIndexed(items) { index, item ->
            Card(
                modifier = Modifier.padding(8.dp),
//                    .background(color = LightGray),
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.cardColors(containerColor = LightGray)
            ) {
                Row(
                    modifier = Modifier.padding(
                        start = 4.dp,
                        end = 4.dp,
                        top = 2.dp,
                        bottom = 2.dp
                    )
                ) {
                    val season = item.keys.elementAt(index)
                    val tier = item.getValue(season)
                    Text(
                        text = season,
                        style = TextStyle(
                            color = SeasonInformationTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                    Text(
                        text = tier,
                        style = TextStyle(color = SeasonInformationTextColor, fontSize = 12.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun TierInformation(summonerHistoryItems: List<Summoner.Item>) {
    LazyRow {
        items(items = summonerHistoryItems) {
            TierItem(it)
        }
    }
}

@Composable
fun TierItem(item: Summoner.Item) {
    val queueType = when (item.queueType.uppercase()) {
        QueueType.RANKED_SOLO_5X5.name -> stringResource(id = R.string.match_solo_rank)
        QueueType.RANKED_FLEX_SR.name -> stringResource(id = R.string.match_free_rank)
        else -> return
    }
    val tier = "${item.tier} ${item.rank}"
    val tierImage = painterResource(
        id = when (item.tier) {
            Tier.IRON.name -> R.drawable.iron
            Tier.BRONZE.name -> R.drawable.bronze
            Tier.SILVER.name -> R.drawable.silver
            Tier.GOLD.name -> R.drawable.gold
            Tier.PLATINUM.name -> R.drawable.platinum
            Tier.DIAMOND.name -> R.drawable.diamond
            Tier.MASTER.name -> R.drawable.master
            Tier.GRANDMASTER.name -> R.drawable.grandmaster
            Tier.CHALLENGER.name -> R.drawable.challenger
            else -> R.drawable.unranked
        }
    )
//    Card(
//        modifier = Modifier
//            .padding(4.dp)
//            .border(width = 2.dp, color = LightGray, RoundedCornerShape(4.dp))
//            .fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//    ) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 2.dp, color = LightGray, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(color = Color.White)
            .height(100.dp)
    ) {
        Image(
            painter = tierImage,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
//                    .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(color = BackgroundPrimaryColor)
                    .padding(2.dp),
                text = queueType,
                style = TextStyle(color = ButtonTextColor, fontSize = 12.sp)
            )
            Text(
                text = tier,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "${item.leaguePoints} LP",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = SeasonInformationTextColor
                )
            )
            Text(
                text = "${item.wins}승 ${item.losses}패 " +
                    "(${calculateWinRate(item.wins, item.losses)}%)",
                style = TextStyle(fontSize = 12.sp, color = SeasonInformationTextColor)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MatchHistoryCard(item: MatchHistory) {
    Log.d("##", "item: $item")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResultMatchHistory(
            Modifier
                .background(color = Color.Red)
                .weight(1.5f)
                .padding(top = 24.dp, bottom = 24.dp, start = 4.dp, end = 4.dp)
                .align(Alignment.CenterVertically)
        )
        ResultInformation(
            Modifier
                .weight(9f)
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun RoundImage(
    @DrawableRes imageRes: Int,
    imageSize: Dp,
    cornerRadius: Dp,
    contentDescription: String? = null
) {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .size(imageSize),
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription
    )
}

@Composable
fun ResultInformation(modifier: Modifier) {
    Column(modifier) {
        ResultInformationTop()
        ResultInformationBottom()
    }
}

@Composable
fun ResultMatchHistory(modifier: Modifier) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "패",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
        )
        Divider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "15:57",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun ResultInformationTop() {
    Row(
        modifier = Modifier.fillMaxWidth()
//            .fillMaxHeight()
    ) {
        RoundImage(
            imageRes = R.drawable.champion_leblanc,
            imageSize = 50.dp,
            cornerRadius = 10.dp
        )
        Column(
            modifier = Modifier
//                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 25.dp)
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundImage(
                    imageRes = R.drawable.summoner_spell_ignite,
                    imageSize = 20.dp,
                    cornerRadius = 5.dp
                )
                RoundImage(
                    imageRes = R.drawable.summoner_rune_electrocute,
                    imageSize = 20.dp,
                    cornerRadius = 10.dp
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "10/14/12",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "개인/2인 랭크",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 11.sp,
                        textAlign = TextAlign.End
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 25.dp)
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundImage(
                    imageRes = R.drawable.summoner_spell_flash,
                    imageSize = 20.dp,
                    cornerRadius = 5.dp
                )
                RoundImage(
                    imageRes = R.drawable.summoner_rune_sorcery,
                    imageSize = 20.dp,
                    cornerRadius = 10.dp
                )
                Text(
                    modifier = Modifier,
                    text = "킬 관여 58%",
                    style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "1일전",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 11.sp,
                        textAlign = TextAlign.End
                    )
                )
            }
        }
    }
}

@Composable
fun ResultInformationBottom() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.accessories_farsight_alteration,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(color = MultiKillBackgroundColor)
                .padding(
                    top = 2.dp,
                    bottom = 2.dp,
                    start = 4.dp,
                    end = 4.dp
                ),
            text = "더블킬",
            style = TextStyle(fontSize = 10.sp, color = Color.Red)
        )
    }
}

@Preview
@Composable
fun MatchHistoryPreview() {
}

fun calculateWinRate(wins: Int, losses: Int): Int {
    val totalGames = wins + losses
    if (totalGames == 0) {
        return 0
    }
    return (wins.toDouble() / totalGames.toDouble() * 100).toInt()
}
