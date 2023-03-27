package gg.lol.android.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import gg.lol.android.ui.theme.PrimaryColor

@Composable
fun IconFavorite(modifier: Modifier = Modifier, isFavorite: Boolean) {
    Icon(
        modifier = modifier,
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        contentDescription = null,
        tint = if (isFavorite) PrimaryColor else Color.Black
    )
}

@Composable
fun NetworkError(modifier: Modifier = Modifier) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        confirmButton = { Text(text = "확인") },
        text = { Text(text = "네트워크 에러 발생") }
    )
}

@Composable
fun LoadingView() {
    LoadingIndicator()
    TouchBlocker()
}

@Composable
fun LoadingIndicator() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun TouchBlocker() {
    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { } }
    )
}
