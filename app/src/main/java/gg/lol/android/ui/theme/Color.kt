package gg.lol.android.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ColorBackground = Color(0xFFF5F7FA)
val LightGray = Color(0xFFF3F5F7)
val PrimaryColor = Color(0xFF5F81E4)

val BackgroundPrimaryColor = Color(0xFFF0F4FF)
val ButtonTextColor = Color(0xFF396BF6)

val MultiKillBackgroundColor = Color(0xFFF9ECEC)
val SeasonInformationBackgroundColor = Color(0xFFF5F7FA)
val SeasonInformationTextColor = Color(0xFF999999)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun getTextFieldNoUnderLine() = TextFieldDefaults.textFieldColors(
    textColor = Color.Gray,
    disabledTextColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)
