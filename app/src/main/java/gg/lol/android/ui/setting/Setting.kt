package gg.lol.android.ui.setting

import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gg.lol.android.BuildConfig
import gg.lol.android.R
import gg.lol.android.ui.account.LoginActivity

@Composable
fun SettingScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 32.dp, start = 8.dp),
            text = stringResource(id = R.string.setting),
            style = MaterialTheme.typography.headlineLarge
        )
        CreateHead(R.string.login) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
        CreateSpacer()
        CreateTitle(R.string.notify_push)
        CreateBody(R.string.notify_game_info)
        CreateBody(R.string.notify_community)
        CreateSpacer()
        CreateHead(R.string.setting_champion, subStrRes = R.string.ingame)
        CreateSpacer()
        CreateHead(R.string.setting_theme, subStrRes = R.string.system)
        CreateSpacer()
        CreateHead(R.string.setting_language, subStrRes = R.string.korean)
        CreateSpacer()
        CreateHead(R.string.setting_bug)
        CreateHead(R.string.setting_review)
        CreateSpacer()
        CreateHead(R.string.setting_terms)
        CreateHead(R.string.setting_privacy)
        CreateSpacer()
        CreateHead(R.string.setting_privacy)
        CreateHead(mainStr = BuildConfig.VERSION_NAME, arrowIconEnable = false)
    }
}

@Composable
fun CreateSpacer() {
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(8.dp)
    )
}

@Composable
fun CreateTitle(@StringRes mainStrRes: Int) {
    Text(
        text = stringResource(id = mainStrRes),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(all = 8.dp)
    )
}

@Composable
fun CreateBody(@StringRes stringRes: Int) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(all = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = stringResource(stringRes),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
        val checkedState = remember { mutableStateOf(false) }
        Switch(
            checked = checkedState.value,
            { checkedState.value = it }
        )
    }
}

@Composable
fun CreateHead(
    @StringRes mainStrRes: Int? = null,
    mainStr: String = "",
    @StringRes subStrRes: Int? = null,
    arrowIconEnable: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
            .clickable(onClick = { onClick.invoke() }),
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = mainStrRes?.let { stringResource(it) } ?: mainStr,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge
        )
        subStrRes?.let {
            Text(
                text = stringResource(it),
                textAlign = TextAlign.End,
                color = Color.Gray
            )
        }
        if (arrowIconEnable) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.keyboard_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun SettingPreview() {
    SettingScreen()
}