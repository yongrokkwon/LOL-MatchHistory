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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import gg.lol.android.BuildConfig
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
import gg.lol.android.util.QueueTypeExtensions.toName
import gg.lol.android.util.TierExtensions.toDrawable
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.QueueType
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.Tier
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.math.roundToInt

enum class MultiKillType(@StringRes val id: Int) {
    DOUBLE(R.string.match_double_kill),
    TRIPLE(R.string.match_triple_kill),
    QUAD(R.string.match_quadra_kill),
    PENTA(R.string.match_penta_kill)
}

@Composable
fun MatchHistoryView(
    viewModel: MatchHistoryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current as Activity

    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Success -> MatchHistoryList(viewModel, state.data)
        is UiState.Error -> {
            state.error?.printStackTrace()
            NetworkError(modifier = Modifier.clickable { context.finish() })
        }
        is UiState.Loading -> LoadingView()
    }
}

@Composable
fun MatchHistoryList(viewModel: MatchHistoryViewModel, summoner: Summoner) {
    val latestVersion = viewModel.latestVersion.collectAsState().value
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
        Header(latestVersion, summoner)
        MatchHistoryUpdate(matchHistories, viewModel, summoner.puuid)
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
                        MatchHistoryCard(
                            latestVersion,
                            matchHistory,
                            it,
                            champions,
                            spells,
                            runes,
                            items
                        )
                    } ?: MatchHistoryErrorView()
                }
            }
        } else {
            LoadingView()
        }
    }
}

@Composable
fun Header(latestVersion: String, summoner: Summoner) {
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
                    painter = rememberAsyncImagePainter(
                        BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                            "/img/profileicon/" + summoner.profileIconId + ".png"
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(50.dp))
                        .background(color = Color.Gray)
                        .padding(start = 4.dp, end = 4.dp),
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
            }
        }
    }
}

@Composable
fun MatchHistoryUpdate(
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
fun TierInformation(summonerHistoryBodies: List<Summoner.Body>) {
    LazyRow {
        items(items = summonerHistoryBodies) {
            TierItem(it)
        }
    }
}

@Composable
fun TierItem(item: Summoner.Body) {
    val queueTypeNameRes = item.queueType.toName()
    val tier = Tier.valueOf(item.tier, item.rank)
    val winRate = calculateWinRate(item.wins, item.losses)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 2.dp, color = LightGray, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(color = Color.White)
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = tier.toDrawable()),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(color = BackgroundPrimaryColor)
                    .padding(2.dp),
                text = stringResource(id = queueTypeNameRes),
                style = TextStyle(color = ButtonTextColor, fontSize = 12.sp)
            )
            Text(
                text = tier.toName(),
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
    }
}

@Composable
fun MatchHistoryErrorView() {
    Text("MatchHistory Load Error")
}

@Composable
fun MatchHistoryCard(
    latestVersion: String,
    matchHistory: MatchHistory,
    participant: MatchHistory.Info.Participant,
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
            participant,
            Modifier
                .background(
                    color = if (participant.win) {
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
            latestVersion,
            matchHistory,
            participant,
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
    latestVersion: String,
    matchHistory: MatchHistory,
    participant: MatchHistory.Info.Participant,
    champions: List<Champion>,
    spells: List<Spell>,
    runes: List<Rune>,
    items: List<Item>
) {
    Column(modifier) {
        ResultInformationTop(
            latestVersion,
            matchHistory,
            participant,
            champions,
            spells,
            runes,
            items
        )
        ResultInformationBottom(latestVersion, participant, items)
    }
}

@Composable
fun ResultMatchHistory(
    matchHistory: MatchHistory,
    participant: MatchHistory.Info.Participant,
    modifier: Modifier
) {
    val durationInSeconds = matchHistory.info.gameDuration
    val minutes = durationInSeconds / 60
    val seconds = durationInSeconds % 60

    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                id = if (participant.win) {
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
    latestVersion: String,
    matchHistory: MatchHistory,
    participant: MatchHistory.Info.Participant,
    champions: List<Champion>,
    spells: List<Spell>,
    runes: List<Rune>,
    items: List<Item>
) {
    val champion = champions.find { it.key.toInt() == participant.championId }
    val spell01 = spells.find { it.key.toInt() == participant.summoner1Id }
    val spell02 = spells.find { it.key.toInt() == participant.summoner2Id }
    val rune1 = runes.find { it.id == participant.perks.styles[0].style }
        ?.bodies?.find { it.id == participant.perks.styles[0].selections[0].perk }
    val rune2 = runes.find { it.id == participant.perks.styles[1].style }
    val queueTypeRes = QueueType.fromQueueId(matchHistory.info.queueId)?.toName()
        ?: QueueType.ETC.toName()
    val dateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(matchHistory.info.gameEndTimestamp),
        ZoneId.systemDefault()
    )
    val gameEndTime = getTimeDiffDuration(dateTime)
    val strTime = when {
        gameEndTime.toMinutes() < 1 -> stringResource(R.string.match_just_now)
        gameEndTime.toHours() < 1 -> stringResource(
            R.string.match_minute,
            gameEndTime.toMinutes().toInt()
        )
        gameEndTime.toHours() < 24 -> stringResource(
            R.string.match_hour,
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
        it.teamId == participant.teamId
    }?.objectives?.champion?.kills ?: 0
    val killInvolvementRate =
        calculateKillInvolvementRate(participant.kills, participant.assists, totalKills)
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(50.dp),
            painter = rememberAsyncImagePainter(
                BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                    "/img/champion/" + champion?.imagePath
            ),
            contentDescription = null
        )
        Column(
            modifier = Modifier
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
                        .size(20.dp)
                        .padding(start = 4.dp),
                    painter = rememberAsyncImagePainter(
                        BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                            "/img/spell/" + spell01?.imagePath
                    ),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(20.dp)
                        .padding(start = 4.dp),
                    painter = rememberAsyncImagePainter(
                        BuildConfig.DDRAGON_URL + "/cdn/img/" + rune1?.icon
                    ),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = stringResource(
                        id = R.string.match_kda,
                        participant.kills,
                        participant.deaths,
                        participant.assists
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
                    text = stringResource(id = queueTypeRes),
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
                        BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                            "/img/spell/" + spell02?.imagePath
                    ),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(20.dp)
                        .padding(start = 4.dp),
                    painter = rememberAsyncImagePainter(
                        BuildConfig.DDRAGON_URL + "/cdn/img/" + rune2?.icon
                    ),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
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
fun ResultInformationBottom(
    latestVersion: String,
    participant: MatchHistory.Info.Participant,
    items: List<Item>
) {
    val item0 = items.find { it.id.toInt() == participant.item0 }
    val item1 = items.find { it.id.toInt() == participant.item1 }
    val item2 = items.find { it.id.toInt() == participant.item2 }
    val item3 = items.find { it.id.toInt() == participant.item3 }
    val item4 = items.find { it.id.toInt() == participant.item4 }
    val item5 = items.find { it.id.toInt() == participant.item5 }
    val item6 = items.find { it.id.toInt() == participant.item6 }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item0?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item1?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item2?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item3?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item4?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item5?.full
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(20.dp)
                    .background(LightGray),
                painter = rememberAsyncImagePainter(
                    BuildConfig.DDRAGON_URL + "/cdn/" + latestVersion +
                        "/img/item/" + item6?.full
                ),
                contentDescription = null
            )
        }

        val multiKillType = when {
            participant.doubleKills != 0 -> MultiKillType.DOUBLE
            participant.tripleKills != 0 -> MultiKillType.TRIPLE
            participant.quadraKills != 0 -> MultiKillType.QUAD
            participant.pentaKills != 0 -> MultiKillType.PENTA
            else -> null
        }
        multiKillType?.let {
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
                text = stringResource(id = it.id),
                style = TextStyle(fontSize = 10.sp, color = Color.Red)
            )
        }
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
