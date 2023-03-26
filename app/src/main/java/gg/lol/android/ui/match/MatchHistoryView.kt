package gg.lol.android.ui.match

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import coil.compose.rememberAsyncImagePainter
import gg.lol.android.R
import gg.lol.android.ui.UiState
import gg.lol.android.ui.theme.BackgroundPrimaryColor
import gg.lol.android.ui.theme.ButtonTextColor
import gg.lol.android.ui.theme.LightGray
import gg.lol.android.ui.theme.MultiKillBackgroundColor
import gg.lol.android.ui.theme.PrimaryColor
import gg.lol.android.ui.theme.SeasonInformationTextColor
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.ui.view.NetworkError
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.models.Summoner
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.math.roundToInt

// TODO https://static.developer.riotgames.com/docs/lol/queues.json
enum class QueueType(@StringRes val resId: Int, val queueId: Int) {
    NORMAL(R.string.match_normal, 400),
    RANKED_SOLO_5X5(R.string.match_solo_rank, 420),
    RANKED_FLEX_SR(R.string.match_flex_rank, 440),
    ARAM(R.string.match_aram, 450),
    CLASH(R.string.match_clash, 700),
    AI_01(R.string.match_ai, 820),
    AI_02(R.string.match_ai, 830),
    AI_03(R.string.match_ai, 840),
    AI_04(R.string.match_ai, 850),
    URF(R.string.match_urf, 900),
    PORO(R.string.match_poro, 920),
    OFA(R.string.match_ofa, 1020),
    TUTORIAL_01(R.string.match_tutorial, 2000),
    TUTORIAL_02(R.string.match_tutorial, 2010),
    TUTORIAL_03(R.string.match_tutorial, 2020),
    ETC(R.string.match_etc, 0)
}

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
    val champions = viewModel.champions
    val spells = viewModel.spells
    val runes = viewModel.runes
    val items = viewModel.items

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
                    matchHistory?.info?.participants?.find { it.puuid == summoner.puuid }?.let {
                        MatchHistoryCard(matchHistory, it, champions, spells, runes, items)
                    } ?: ErrorView()
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
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
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
        onClick = { viewModel.getMatchHistories(puuid) }
    )
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
        QueueType.RANKED_FLEX_SR.name -> stringResource(id = R.string.match_flex_rank)
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
    val winRate = calculateWinRate(
        item.wins,
        item.losses
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
                text = stringResource(
                    id = R.string.match_match_history,
                    item.wins,
                    item.losses,
                    winRate
                ),
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
fun ErrorView() {
    Text("MatchHistory Load Error")
}

@Composable
fun MatchHistoryCard(
    matchHistory: MatchHistory,
    item: MatchHistory.Info.Participant,
    champions: List<Champion>,
    spells: List<Spell>,
    runes: List<Rune>,
    items: List<Item>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResultMatchHistory(
            matchHistory,
            item,
            Modifier
                .background(
                    color = if (item.win) {
                        PrimaryColor
                    } else {
                        Color.Red
                    }
                )
                .padding(top = 24.dp, bottom = 24.dp, start = 4.dp, end = 4.dp)
                .weight(1.5f)
                .align(Alignment.CenterVertically)
        )
        ResultInformation(
            Modifier
                .weight(9f)
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically),
            matchHistory,
            item,
            champions,
            spells,
            runes,
            items
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
fun ResultInformation(
    modifier: Modifier,
    matchHistory: MatchHistory,
    item: MatchHistory.Info.Participant,
    champions: List<Champion>,
    spells: List<Spell>,
    runes: List<Rune>,
    items: List<Item>
) {
    Column(modifier) {
        ResultInformationTop(matchHistory, item, champions, spells, runes, items)
        ResultInformationBottom(item, items)
    }
}

@Composable
fun ResultMatchHistory(
    matchHistory: MatchHistory,
    item: MatchHistory.Info.Participant,
    modifier: Modifier
) {
    val durationInSeconds = matchHistory.info.gameDuration
    val minutes = durationInSeconds / 60
    val seconds = durationInSeconds % 60

    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                id = if (item.win) {
                    R.string.match_victory
                } else {
                    R.string.match_defeat
                }
            ),
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
        )
        Divider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.match_time, minutes, seconds),
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun ResultInformationTop(
    matchHistory: MatchHistory,
    item: MatchHistory.Info.Participant,
    champions: List<Champion>,
    spells: List<Spell>,
    runes: List<Rune>,
    items: List<Item>
) {
    val champion = champions.find { it.key.toInt() == item.championId }
    val spell01 = spells.find { it.key.toInt() == item.summoner1Id }
    val spell02 = spells.find { it.key.toInt() == item.summoner2Id }
    val rune1 = runes.find { it.id == item.perks.styles[0].style }
    val rune2 = runes.find { it.id == item.perks.styles[1].style }
    val queueStrRes = when (matchHistory.info.queueId) {
        QueueType.NORMAL.queueId -> QueueType.NORMAL.resId
        QueueType.RANKED_SOLO_5X5.queueId -> QueueType.RANKED_SOLO_5X5.resId
        QueueType.RANKED_FLEX_SR.queueId -> QueueType.RANKED_FLEX_SR.resId
        QueueType.ARAM.queueId -> QueueType.ARAM.resId
        QueueType.CLASH.queueId -> QueueType.CLASH.resId
        in QueueType.AI_01.queueId..QueueType.AI_04.queueId -> QueueType.AI_01.resId
        QueueType.URF.queueId -> QueueType.URF.resId
        QueueType.PORO.queueId -> QueueType.PORO.resId
        QueueType.OFA.queueId -> QueueType.OFA.resId
        in QueueType.TUTORIAL_01.queueId..QueueType.TUTORIAL_03.queueId -> {
            QueueType.TUTORIAL_01.resId
        }
        else -> QueueType.ETC.resId
    }
    val dateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(matchHistory.info.gameEndTimestamp),
        ZoneId.systemDefault()
    )
    val gameEndTime = getTimeDiffDuration(dateTime)
    val strTime = when {
        gameEndTime.toMinutes() < 1 -> stringResource(R.string.match_just_now)
        gameEndTime.toHours() < 1 -> stringResource(
            R.string.match_minute,
            gameEndTime.toHours().toInt()
        )
        gameEndTime.toDays() < 7 -> stringResource(R.string.match_day, gameEndTime.toDays().toInt())
        else -> stringResource(
            R.string.match_date,
            dateTime.year,
            dateTime.monthValue,
            dateTime.dayOfMonth
        )
    }
    val totalKills = matchHistory.info.teams.find {
        it.teamId == item.teamId
    }?.objectives?.champion?.kills ?: 0
    val killInvolvementRate = calculateKillInvolvementRate(item.kills, item.assists, totalKills)
    Row(
        modifier = Modifier.fillMaxWidth()
//            .fillMaxHeight()
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(50.dp),
            painter = rememberAsyncImagePainter(
                "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/champion/" +
                    champion?.imagePath
            ),
            contentDescription = null
        )
//        RoundImage(
//
//            imageRes = R.drawable.champion_leblanc,
//            imageSize = 50.dp,
//            cornerRadius = 10.dp
//        )
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
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(20.dp),
                    painter = rememberAsyncImagePainter(
                        "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/spell/" +
                            spell01?.imagePath
                    ),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(20.dp),
                    painter = rememberAsyncImagePainter(
                        "https://ddragon.leagueoflegends.com/cdn/img/" +
                            rune1?.icon
                    ),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = stringResource(
                        id = R.string.match_kda,
                        item.kills,
                        item.deaths,
                        item.assists
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = queueStrRes),
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
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(20.dp),
                    painter = rememberAsyncImagePainter(
                        "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/spell/" +
                            spell02?.imagePath
                    ),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(20.dp),
                    painter = rememberAsyncImagePainter(
                        "https://ddragon.leagueoflegends.com/cdn/img/" +
                            rune2?.icon
                    ),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(
                        id = R.string.match_kill_involvement_rate,
                        killInvolvementRate
                    ),
                    style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = strTime,
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
fun ResultInformationBottom(item: MatchHistory.Info.Participant, items: List<Item>) {
    val item0 = items.find { it.id.toInt() == item.item0 }
    val item1 = items.find { it.id.toInt() == item.item1 }
    val item2 = items.find { it.id.toInt() == item.item2 }
    val item3 = items.find { it.id.toInt() == item.item3 }
    val item4 = items.find { it.id.toInt() == item.item4 }
    val item5 = items.find { it.id.toInt() == item.item5 }
    val item6 = items.find { it.id.toInt() == item.item6 }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item0?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item1?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item2?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item3?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item4?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item5?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp),
                painter = rememberAsyncImagePainter(
                    "https://ddragon.leagueoflegends.com/cdn/13.6.1/img/item/" +
                        item6?.full
                ),
                contentDescription = null
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

fun calculateKillInvolvementRate(kills: Int, assists: Int, totalKills: Int): Int {
    val killContribution = kills + assists
    val killInvolvementRate = (killContribution.toDouble() / totalKills.toDouble()) * 100.0
    return killInvolvementRate.roundToInt()
}

fun getTimeDiffDuration(dateTime: LocalDateTime): Duration {
    val now = LocalDateTime.now()
    val diff = Duration.between(dateTime, now)
    dateTime.year

    return diff
}
