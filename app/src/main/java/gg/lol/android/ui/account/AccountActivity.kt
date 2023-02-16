package gg.lol.android.ui.account

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gg.lol.android.R
import gg.lol.android.ui.theme.LOLGGTheme

const val ROUTE_LOGIN = "LOGIN"
const val ROUTE_SIGNUP = "SIGNUP"
const val ROUTE_INFO_INPUT = "INFO_INPUT"
const val ROUTE_SIGNUP_EMAIL_SEND = "ROUTE_SIGNUP_EMAIL_SEND"

class AccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                AccountView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountView() {
    val navController = rememberNavController()
    val context = LocalContext.current as Activity
    val appBarTitle = remember { mutableStateOf(R.string.login) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
            title = { Text(stringResource(id = appBarTitle.value)) },
            navigationIcon = {
                IconButton(onClick = {
                    if (navController.backQueue.size == 2) context.finish()
                    else navController.popBackStack()
                }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
        )
        NavHost(navController, startDestination = ROUTE_LOGIN) {
            composable(route = ROUTE_LOGIN) {
                appBarTitle.value = R.string.login
                LoginScreen(navController)
            }
            // TODO SignUP To Birth
            composable(route = ROUTE_SIGNUP) {
                appBarTitle.value = R.string.signup
                BirthInputScreen(navController)
            }
            composable(route = ROUTE_INFO_INPUT) {
                appBarTitle.value = R.string.signup
                InfoInputScreen(navController)
            }
            composable(route = ROUTE_SIGNUP_EMAIL_SEND) {
                appBarTitle.value = R.string.signup
                SignUpEmailSendScreen(navController)
            }
        }
    }
}
