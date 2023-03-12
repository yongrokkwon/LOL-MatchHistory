package gg.lol.android.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun IconFavorite(isFavorite: Boolean) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = null,
        tint = if (isFavorite) Color.Yellow else Color.White
    )
}