package gg.lol.android.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import gg.lol.android.R
import gg.lol.android.ui.theme.PrimaryColor

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

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
fun AlertErrorDialog(throwable: Throwable, confirmOnClick: () -> Unit = {}) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Text(
                    modifier = Modifier.clickable {
                        openDialog.value = false
                        confirmOnClick.invoke()
                    },
                    text = stringResource(id = R.string.ok)
                )
            },
            text = {
                Text(
                    text = throwable.message ?: stringResource(id = R.string.internal_error)
                )
            }
        )
    }
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
