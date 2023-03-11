package gg.lol.android.ui.record

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import gg.lol.android.R
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.ui.theme.MultiKillBackgroundColor

@Composable
fun RecordScreen(
    viewModel: RecordViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val searchHistories = viewModel.searchHistories.observeAsState(emptyList()).value
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (searchHistories.isEmpty()) {
            Text(text = "No items to display")
        } else {
            LazyColumn(
                modifier = Modifier
            ) {
                items(searchHistories) { item ->
                    SearchHistoryCard(item)
                }
            }
        }
    }
}

@Composable
fun SearchHistoryCard(item: SearchHistory) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResultRecord(
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
fun ResultRecord(modifier: Modifier) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "패",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center),
        )
        Divider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "15:57",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center),
        )
    }
}

@Composable
fun ResultInformationTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
                    ),
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "개인/2인 랭크",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 11.sp,
                        textAlign = TextAlign.End
                    ),
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
                    modifier = Modifier.padding(start = 4.dp),
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
                    ),
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
fun SearchPreview() {
//    RecordScreen()
}
