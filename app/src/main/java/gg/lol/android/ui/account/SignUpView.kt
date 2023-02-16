@file:OptIn(ExperimentalMaterial3Api::class)

package gg.lol.android.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import gg.lol.android.R
import gg.lol.android.ui.component.BirthVisualTransformation
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.Typography

const val BIRTH_LENGTH = 8

@Composable
fun BirthInputScreen(navController: NavHostController? = null) {
    val birthInput = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .height(50.dp)
                .padding(top = 8.dp),
            painter = painterResource(id = R.drawable.progress_first),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(text = stringResource(id = R.string.signup_birth), style = Typography.titleLarge)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = birthInput.value,
            onValueChange = { if (it.length <= BIRTH_LENGTH) birthInput.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(stringResource(id = R.string.signup_birth_hint)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true,
            visualTransformation = BirthVisualTransformation()
        )
        Text(
            text = stringResource(id = R.string.signup_birth_guide),
            style = GUIDE_STYLE
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { navController?.navigate(ROUTE_INFO_INPUT) }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.next),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable
fun InfoInputScreen(navController: NavHostController? = null) {

}

@Composable
fun SignUpPreview() {
    BirthInputScreen()
}