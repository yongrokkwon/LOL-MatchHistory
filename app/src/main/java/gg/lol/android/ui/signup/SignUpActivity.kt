package gg.lol.android.ui.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import gg.lol.android.ui.theme.LOLGGTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOLGGTheme {
                SignUpView()
            }
        }
    }
}

@Composable
fun SignUpView() {
    SignUpScreen()
}