package gg.lol.android.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import gg.lol.android.R

@Composable
fun SignUpScreen() {
    Column() {

        Image(painter = painterResource(id = R.drawable.progress_first), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.progress_second), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.progress_three), contentDescription = null)
    }
}

@Composable
fun SignUpPreview() {
    SignUpScreen()
}