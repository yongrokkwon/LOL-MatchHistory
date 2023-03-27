package gg.lol.android.ui.account

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gg.lol.android.R
import gg.lol.android.ui.theme.LOLMatchHistoryTheme

// TODO sealed class refactor
const val ROUTE_LOGIN = "LOGIN"
const val ROUTE_SIGNUP = "SIGNUP"
const val ROUTE_INFO_INPUT = "INFO_INPUT"
const val ROUTE_SIGNUP_EMAIL_SEND = "ROUTE_SIGNUP_EMAIL_SEND"

@AndroidEntryPoint
class AccountActivity : ComponentActivity() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLMatchHistoryTheme {
                AccountView(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountView(viewModel: AccountViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current as Activity
    val appBarTitle by viewModel.appBarTitle.observeAsState(initial = "")
    // TODO Remember to ViewModel LiveData
    val screenLastCheck = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
            title = { Text(text = appBarTitle) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (screenLastCheck.value) {
                            context.finish()
                        } else {
                            if (navController.backQueue.size == 2) {
                                context.finish()
                            } else {
                                navController.popBackStack()
                            }
                        }
                        screenLastCheck.value = false
                    }
                ) {
                    if (screenLastCheck.value) {
                        Icon(Icons.Filled.Close, null)
                    } else {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            }
        )
        NavHost(navController, startDestination = ROUTE_LOGIN) {
            composable(route = ROUTE_LOGIN) {
                screenLastCheck.value = false
                viewModel.setAppBarTitle(stringResource(id = R.string.login))
                LoginScreen(navController)
            }
            // TODO SignUP To Birth
            composable(route = ROUTE_SIGNUP) {
                screenLastCheck.value = false
                viewModel.setAppBarTitle(stringResource(id = R.string.signup))
                BirthInputScreen(navController)
            }
            composable(route = ROUTE_INFO_INPUT) {
                screenLastCheck.value = false
                viewModel.setAppBarTitle(stringResource(id = R.string.signup))
                InfoInputScreen(navController, viewModel)
            }
            composable(route = ROUTE_SIGNUP_EMAIL_SEND) {
                screenLastCheck.value = true
                viewModel.setAppBarTitle(stringResource(id = R.string.signup))
                SignUpEmailSendScreen(navController, viewModel)
            }
        }
    }
}
