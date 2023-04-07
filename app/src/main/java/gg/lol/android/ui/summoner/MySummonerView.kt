package gg.lol.android.ui.summoner

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gg.lol.android.R
import gg.lol.android.ui.UiState
import gg.lol.android.ui.theme.LOLMatchHistoryTheme
import gg.lol.android.ui.theme.PrimaryColor
import gg.lol.android.ui.theme.SearchHint
import gg.lol.android.ui.view.AlertErrorDialog
import gg.lol.android.ui.view.LoadingView
import gg.lol.android.util.Extensions.showToast

@Composable
fun MySummonerView(navController: NavController, viewModel: MySummonerViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is UiState.Loading -> LoadingView()
        is UiState.Error -> AlertErrorDialog(throwable = state.error)
        is UiState.Success -> Unit
    }
    LaunchedEffect(state) {
        if (state is UiState.Success) {
            state.data?.let { navController.popBackStack() }
        }
    }
    RenderView(navController, viewModel)
}

@Composable
fun RenderView(navController: NavController, viewModel: MySummonerViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Icon(
            modifier = Modifier.padding(top = 16.dp)
                .clickable { navController.popBackStack() },
            imageVector = Icons.Default.Close,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.my_summoner_title),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        BasicTextField(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(6.dp)
                ).padding(top = 0.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)
                .height(50.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            value = viewModel.summonerName.value,
            onValueChange = { viewModel.setSummonerName(it) },
            decorationBox = { innerTextField ->
                Row(
                    Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        if (viewModel.summonerName.value.isEmpty()) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.my_summoner_hint),
                                style = SearchHint
                            )
                        } else {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear text",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .align(Alignment.CenterEnd)
                                    .clickable { viewModel.setSummonerName("") }
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        val scope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            shape = RoundedCornerShape(10),
            content = {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.complete),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            onClick = {
                if (viewModel.summonerName.value.isEmpty()) {
                    context.showToast(R.string.my_summoner_toast)
                } else {
                    viewModel.getRemoteSummoner()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySummonerViewPreview() {
    LOLMatchHistoryTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Icon(
                modifier = Modifier.padding(top = 16.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.my_summoner_title),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            BasicTextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    ),
                value = "",
                onValueChange = { },
                decorationBox = { innerTextField ->
                    Row(
                        Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier.weight(1f)
                                .padding(start = 4.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 12.dp, bottom = 12.dp, start = 4.dp, end = 4.dp),
                                text = stringResource(id = R.string.my_summoner_hint),
                                style = SearchHint
                            )
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear text",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .align(Alignment.CenterEnd)
                                    .clickable { }
                            )
                            innerTextField()
                        }
                    }
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(10),
                content = {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.complete),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                onClick = { }
            )
        }
    }
}
