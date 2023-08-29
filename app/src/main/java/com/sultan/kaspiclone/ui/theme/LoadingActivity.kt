package com.sultan.kaspiclone.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Preview(showBackground = true)
@Composable
fun EasyDemo() {
    var progress by remember { mutableStateOf(0.0f) }
    val scope = rememberCoroutineScope()


    scope.launch {
        while (progress < 1f) {
            progress += 0.1f
            delay(7000L)
        }

    }


    LinearProgressIndicator(progress = progress , color = if(progress >= 1f) Color.White else Color.Blue)


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SwipeRefreshCompose() {
    var refreshing by remember { mutableStateOf(false) }
    val triggerSwipeRefresh: () -> Unit = { refreshing = true }
    val scope = rememberCoroutineScope()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { /* This lambda is not needed since we're using the triggerSwipeRefresh function */ }
    ) {


            if (refreshing) {
                scope.launch {
                    delay(4000)
                }
                refreshing = false

            }
            EasyDemo()

    }
}
