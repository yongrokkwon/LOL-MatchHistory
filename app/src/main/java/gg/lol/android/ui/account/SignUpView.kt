package gg.lol.android.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import gg.lol.android.R
import gg.lol.android.ui.component.BirthVisualTransformation
import gg.lol.android.ui.component.HyperlinkText
import gg.lol.android.ui.theme.GUIDE_STYLE
import gg.lol.android.ui.theme.LightGray
import gg.lol.android.ui.theme.Typography

const val BIRTH_LENGTH = 8

@OptIn(ExperimentalMaterial3Api::class)
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
            onClick = { navController?.navigate(ROUTE_INFO_INPUT) }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.next),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoInputScreen(
    navController: NavHostController? = null,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val email = remember { mutableStateOf("") }
    val nickname = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
            painter = painterResource(id = R.drawable.progress_second),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(id = R.string.signup_info),
            style = Typography.headlineLarge
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LightGray)
                .padding(start = 6.dp, end = 6.dp, top = 8.dp, bottom = 8.dp),
            text = stringResource(id = R.string.signup_info_guide),
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = email.value,
            onValueChange = { email.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(stringResource(id = R.string.signup_info_email_hint)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = nickname.value,
            onValueChange = { nickname.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(stringResource(id = R.string.nickname)) },
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
            placeholder = { Text(stringResource(id = R.string.password)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                viewModel.setEmail(email.value)
                navController?.navigate(route = ROUTE_SIGNUP_EMAIL_SEND)
            }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.signup_info_button),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable
fun SignUpEmailSendScreen(
    navController: NavHostController? = null,
    viewModel: AccountViewModel = hiltViewModel()
) {
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
            painter = painterResource(id = R.drawable.progress_three),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = stringResource(id = R.string.email_guide_title),
            style = Typography.headlineLarge
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.email_guide_info_01, viewModel.email.value ?: ""),
            style = Typography.titleLarge
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.email_guide_info_02),
            style = GUIDE_STYLE
        )
        HyperlinkText(
            fullText = stringResource(id = R.string.email_guide_info_03),
            linkText = listOf(stringResource(id = R.string.email_guide_info_03_link)),
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        HyperlinkText(
            fullText = stringResource(id = R.string.email_guide_info_04),
            linkText = listOf(stringResource(id = R.string.email_guide_info_04_link)),
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            hyperlinks = listOf("SELF"),
            onClick = { navController?.navigate(ROUTE_LOGIN) }
        )
    }
}

@Composable
fun SignUpPreview() {
    BirthInputScreen()
}
