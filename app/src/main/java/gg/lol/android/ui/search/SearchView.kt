package gg.lol.android.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gg.lol.android.R
import gg.lol.android.ui.match.MatchHistoryActivity
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.SearchHint
import gg.lol.android.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
//    val searchHistories = viewModel.searchHistories.observeAsState().value ?: emptyList()
    val context = LocalContext.current as Activity
    val searchWord = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.White).padding(8.dp)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)
                    .clickable { context.finish() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
            BasicTextField(
                value = searchWord.value,
                onValueChange = { searchWord.value = it },
                modifier = Modifier.fillMaxWidth().height(50.dp).border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(6.dp)
                ).onKeyEvent {
                    searchWord.value = searchWord.value.replace("\n", "")
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        startMatchHistoryActivity(context, searchWord.value)
                        return@onKeyEvent true
                    }
                    return@onKeyEvent false
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        startMatchHistoryActivity(context, searchWord.value)
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
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                        Box(Modifier.weight(1f)) {
                            if (searchWord.value.isEmpty()) {
                                Text(
                                    stringResource(id = R.string.search_hint),
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
            modifier = Modifier.padding(top = 8.dp).fillMaxSize().background(color = Color.White)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.search_recent),
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.search_all_delete),
                    style = GUIDE_STYLE,
                    textAlign = TextAlign.End
                )
            }
            if (/*searchHistories.isEmpty()*/ false /*TODO*/) {
                Text(text = "No items to display")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listOf("asd")) { item ->
                        Row(
                            modifier = Modifier.padding(top = 8.dp).clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        MatchHistoryActivity::class.java
                                    ).apply {
                                        putExtra(
                                            MatchHistoryActivity.EXTRA_NICKNAME,
                                            "hide on bush" /*TODO*/
                                        )
                                    }
                                )
                            }
                        ) {
                            Image(
                                modifier = Modifier.clip(RoundedCornerShape(12.dp))
                                    .padding(end = 0.dp).size(40.dp),
                                painter = painterResource(R.drawable.summoner_icon_test), // TODO
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Column(
                                modifier = Modifier.padding(start = 4.dp).weight(1f)
                            ) {
                                Text(text = "hide on bush" /*TODO*/, fontWeight = FontWeight.Bold)
                                Row() {
                                    // TODO
                                    Image(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(
                                            id = R.drawable.search_challenger
                                        ),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Text(text = "C1")
                                }
                            }
                            Row(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.FavoriteBorder,
                                    contentDescription = null
                                ) // TODO Favorite Check
                                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun startMatchHistoryActivity(context: Context, summonerName: String) {
    context.startActivity(
        Intent(
            context,
            MatchHistoryActivity::class.java
        ).apply {
            putExtra(
                MatchHistoryActivity.EXTRA_NICKNAME,
                summonerName
            )
        }
    )
}

@Preview
@Composable
fun SearchPreview() {
//    SearchScreen()
}
