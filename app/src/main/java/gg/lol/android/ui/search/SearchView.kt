@file:OptIn(ExperimentalMaterial3Api::class)

package gg.lol.android.ui.search

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gg.lol.android.R
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.SearchHint
import gg.lol.android.ui.theme.Typography
import gg.lol.android.ui.theme.getTextFieldNoUnderLine

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity
    val searchWord = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    .clickable { context.finish() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    ),
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                value = searchWord.value,
                onValueChange = { searchWord.value = it },
                singleLine = true,
                placeholder = {
                    Text(
                        stringResource(id = R.string.search_hint),
                        style = SearchHint
                    )
                },
                colors = getTextFieldNoUnderLine()
            )
        }

        Column(
            modifier = Modifier
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
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.search_all_delete),
                    style = GUIDE_STYLE,
                    textAlign = TextAlign.End
                )
            }
            LazyColumn {
                // TODO
//                if (itemsList.isEmpty()) {
//                    // Composable view to display when the list is empty
//                    Text(text = "No items to display")
//                } else {
//                    items(itemsList) { item ->
//                        // Composable item to display in the column
//                        Text(text = item.name)
//                    }
//                }
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    SearchScreen()
}