package gg.lol.android.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import gg.lol.android.R
import gg.lol.android.ui.component.HyperlinkText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController? = null) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginStatusRemember = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.login_email)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = email.value,
                onValueChange = { email.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = { Text(stringResource(id = R.string.login_hint_email)) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                singleLine = true
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = password.value,
                onValueChange = { password.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholder = { Text(stringResource(id = R.string.login_hint_password)) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            // 비슷한 체크박스가 여러개 사용될 경우 Component.kt 함수로 커스터마이징해서 사용
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Row(
                    modifier = Modifier.clickable {
                        loginStatusRemember.value = !loginStatusRemember.value
                    }
                ) {
                    Checkbox(checked = loginStatusRemember.value, onCheckedChange = null)
                    Text(
                        text = stringResource(id = R.string.login_status),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                HyperlinkText(
                    fullText = stringResource(id = R.string.login_password),
                    linkText = listOf(stringResource(id = R.string.login_password)),
                    style = TextStyle(textAlign = TextAlign.End),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(id = R.string.login))
            }

            HyperlinkText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                fullText = stringResource(id = R.string.login_signup),
                linkText = listOf(stringResource(id = R.string.login_signup_link)),
                style = TextStyle(textAlign = TextAlign.Center),
                hyperlinks = listOf("SELF"),
                onClick = { navController?.navigate(ROUTE_SIGNUP) }
            )
        }
    }
}

@Composable
fun LoginPreview() {
    LoginScreen()
}
