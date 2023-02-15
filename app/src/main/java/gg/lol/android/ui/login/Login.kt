package gg.lol.android.ui.login

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gg.lol.android.R
import gg.lol.android.ui.component.HyperlinkText
import gg.lol.android.ui.signup.SignUpActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginStatusRemember = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
            title = { Text(stringResource(id = R.string.login)) },
            navigationIcon = {
                IconButton(onClick = { (context as? Activity)?.finish() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
        )
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
                Row(modifier = Modifier.clickable {
                    loginStatusRemember.value = !loginStatusRemember.value
                }) {
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
                onClick = {
                    context.startActivity(Intent(context, SignUpActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun LoginPreview() {
    LoginScreen()
}