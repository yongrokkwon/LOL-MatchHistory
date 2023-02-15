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

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
            title = { Text(stringResource(id = R.string.login)) },
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
            composable(route = ROUTE_LOGIN) { LoginScreen(navController) }
            composable(route = ROUTE_SIGNUP) { SignUpScreen(navController) }
        }
    }
}
