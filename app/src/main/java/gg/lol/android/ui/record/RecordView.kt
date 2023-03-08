@file:OptIn(ExperimentalMaterial3Api::class)

package gg.lol.android.ui.record

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gg.lol.android.R
import gg.lol.android.ui.theme.MultiKillBackgroundColor

@Composable
fun RecordScreen(
    viewModel: RecordViewModel = hiltViewModel()
) {
    val searchHistories = viewModel.searchHistories.observeAsState().value ?: emptyList()
    val context = LocalContext.current as Activity
    val searchWord = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (searchHistories.isEmpty()) {
            Text(text = "No items to display")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(searchHistories) { item ->
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .background(color = Color.Red)
                                .weight(1.5f)
//                                .widthIn(min = 45.dp)
//                                .width(width = 45.dp)
                                .padding(top = 24.dp, bottom = 24.dp, start = 4.dp, end = 4.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            // TODO
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "패",
                                style = TextStyle(color = Color.White),
                                textAlign = TextAlign.Center
                            )
                            Divider(
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "15:57",
                                style = TextStyle(color = Color.White),
                                textAlign = TextAlign.Center
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(9f)
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                Image(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .size(45.dp),
                                    painter = painterResource(id = R.drawable.champion_leblanc),
                                    contentDescription = null
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(start = 4.dp)
//                                        .fillMaxHeight()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp)
                                            .padding(bottom = 2.5.dp),
                                        painter = painterResource(id = R.drawable.summoner_spell_ignite),
                                        contentDescription = null
                                    )
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.summoner_spell_flash),
                                        contentDescription = null
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.summoner_rune_electrocute),
                                        contentDescription = null
                                    )
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.summoner_rune_sorcery),
                                        contentDescription = null
                                    )
                                }
                                // TODO 이미지의 Height가 가장큰 기준이 되므로 FillMaxHeight가 먹지않음. 구조 수정이 필요해보임
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 45.dp)
                                        .padding(start = 4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(45.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = "10/14/12",
                                            style = TextStyle(
                                                color = Color.Black,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            modifier = Modifier.align(Alignment.TopEnd),
                                            text = "개인/2인 랭크",
                                            style = TextStyle(color = Color.Gray, fontSize = 11.sp),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(45.dp)
                                            .padding(start = 4.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = "킬 관여 58%",
                                            style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                                        )
                                        Text(
                                            modifier = Modifier.align(Alignment.TopEnd),
                                            text = "1일전",
                                            style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                                        )
                                    }
                                }
                            }
                            Box(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.item_everfrost),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .size(20.dp),
                                        painter = painterResource(id = R.drawable.accessories_farsight_alteration),
                                        contentDescription = null
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .background(color = MultiKillBackgroundColor),
                                    text = "더블킬",
                                    color = Color.Red,
                                    style = TextStyle(fontSize = 11.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    RecordScreen()
}