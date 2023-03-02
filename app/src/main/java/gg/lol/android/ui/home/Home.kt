package gg.lol.android.ui.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gg.lol.android.R
import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.ui.search.SearchActivity
import gg.lol.android.ui.theme.BackgroundPrimaryColor
import gg.lol.android.ui.theme.ButtonTextColor
import gg.lol.android.ui.theme.GUIDE_STYLE

@Composable
fun HomeScreen(navController: NavController? = null, viewModel: MainViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .height(40.dp)
                .clickable { context.startActivity(Intent(context, SearchActivity::class.java)) },
            painter = painterResource(id = R.drawable.home_search),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        CreateEmptyFavoriteSummonerView()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { }),
        ) {

        }
    }
}

@Composable
fun CreateFavoriteSummonerView() {

}

@Composable
fun CreateEmptyFavoriteSummonerView() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp)
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
                    modifier = Modifier
                        .width(15.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.Yellow
            )
            Text(
                modifier = Modifier.padding(),
                text = stringResource(id = R.string.home_favorite_empty_guide_01),
                fontSize = 12.sp
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.home_favorite_empty_guide_02),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BackgroundPrimaryColor)
                .height(30.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundPrimaryColor
            ),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(text = stringResource(id = R.string.home_favorite_button), color = ButtonTextColor, fontSize = 12.sp)
        }
    }
}

@Composable
fun HomePreview() {
    HomeScreen()
}