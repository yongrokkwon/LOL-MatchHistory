package gg.lol.android.ui.account

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import gg.lol.android.ui.theme.LOLGGTheme

class AccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                LoginView()
            }
        }
    }
}

@Composable
fun LoginView() {
    LoginScreen()
}