package gg.lol.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gg.lol.android.R
import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.ui.navigation.LOLGGRoute

@Composable
fun HomeScreen(navController: NavController? = null, viewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .clickable { navController?.navigate(LOLGGRoute.SEARCH) },
            painter = painterResource(id = R.drawable.home_search),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.empty),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun HomePreview() {
    HomeScreen()
}