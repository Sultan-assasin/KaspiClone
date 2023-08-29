package com.sultan.kaspiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sultan.kaspiclone.ui.theme.EasyDemo
import com.sultan.kaspiclone.ui.theme.SwipeRefreshCompose
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            if (isSystemInDarkTheme()) {
                systemUiController.setSystemBarsColor(
                    color = Color.White
                )
            } else {
                systemUiController.setSystemBarsColor(
                    color = Color.White
                )
            }

            Surface(Modifier.fillMaxSize()) {
                SwipeToRefreshDemo()
            }

//            Column(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                topAppKaspi()
//
//                Spacer(modifier = Modifier.padding(6.dp))
//                listItem(
//                    image = R.drawable.img,
//                    title = "Kaspi Gold",
//                    tint = "*1234",
//                    price = "100000"
//                )
//                Spacer(modifier = Modifier.padding(10.dp))
//                listDepo(
//                    image = R.drawable.img_2,
//                    title = "Открыть Kaspi Red"
//                )
//                Spacer(modifier = Modifier.padding(10.dp))
//
//
//
//                listItem(
//                    image = R.drawable.img_1,
//                    title = "Депозит KZT",
//                    tint = "до 16.10.2025",
//                    price = "100 000 453 23,086"
//                )
//                Divider(
//                    modifier = Modifier.padding(start = 70.dp),
//                    color = Color.Gray,
//                    thickness = 0.5.dp
//                )
//                listDepo(
//                    image = R.drawable.img_2,
//                    title = "Открыть Kaspi Депозит"
//                )
//                Spacer(modifier = Modifier.padding(10.dp))
//
//                listDepo(
//                    image = R.drawable.img_2,
//                    title = "Оформить кредит или рассрочку"
//                )
//                Spacer(modifier = Modifier.padding(10.dp))
//                listDepo(
//                    image = R.drawable.img_3,
//                    title = "Kaspi Бонус",
//                    price = "300₸"
//                )
//
//            }





        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppKaspi() {
    TopAppBar(title = {
        Text(
            text = "Мой Банк",
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.araboto))
        )
    }, navigationIcon = {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Red,
            modifier = Modifier.padding(start = 20.dp)
        )
    })


}

@Composable
fun listItem(image: Int, title: String, tint: String, price: String) {
    Card(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .height(60.dp)
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Artist image",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(40.dp)
                    .width(50.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Column {
                Text(title, fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.araboto)))
                Text(tint, fontFamily = FontFamily(Font(R.font.araboto)))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = " ${price}₸",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.araboto))
            )
        }
    }
}

@Composable
fun listDepo(image: Int, title: String, price: String = "") {
    Card(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .height(60.dp)
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Artist image",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(40.dp)
                    .width(50.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Text(
                    title,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.blue),
                    fontFamily = FontFamily(Font(R.font.araboto))
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = price,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.araboto)),

                )
            }
        }
    }
}


@Composable
fun SwipeToRefreshDemo() {
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Column {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                isRefreshing = true
            },
            indicator = { state, trigger ->
                CustomViewPullRefreshView(state, trigger)
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                topAppKaspi()

                Spacer(modifier = Modifier.padding(6.dp))
                listItem(
                    image = R.drawable.img,
                    title = "Kaspi Gold",
                    tint = "*1234",
                    price = "100000"
                )
                Spacer(modifier = Modifier.padding(10.dp))
                listDepo(
                    image = R.drawable.img_2,
                    title = "Открыть Kaspi Red"
                )
                Spacer(modifier = Modifier.padding(10.dp))



                listItem(
                    image = R.drawable.img_1,
                    title = "Депозит KZT",
                    tint = "до 16.10.2025",
                    price = "100 000 453 23,086"
                )
                Divider(
                    modifier = Modifier.padding(start = 70.dp),
                    color = Color.Gray,
                    thickness = 0.5.dp
                )
                listDepo(
                    image = R.drawable.img_2,
                    title = "Открыть Kaspi Депозит"
                )
                Spacer(modifier = Modifier.padding(10.dp))

                listDepo(
                    image = R.drawable.img_2,
                    title = "Оформить кредит или рассрочку"
                )
                Spacer(modifier = Modifier.padding(10.dp))
                listDepo(
                    image = R.drawable.img_3,
                    title = "Kaspi Бонус",
                    price = "300₸"
                )

            }
        }
        ListDivider()
    }
}
@Composable
fun ListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}


@Composable
fun CustomViewPullRefreshView(
    swipeRefreshState: SwipeRefreshState,
    refreshTriggerDistance: Dp,
    color: Color = Color.Gray
) {
    Box(
        Modifier
            .drawWithCache {
                onDrawBehind {
                    val distance = refreshTriggerDistance.toPx()
                    val progress = (swipeRefreshState.indicatorOffset / distance).coerceIn(0f, 1f)
                    val brush = Brush.verticalGradient(
                        0f to color.copy(alpha = 0.45f),
                        1f to color.copy(alpha = 0f)
                    )
                    drawRect(
                        brush = brush,
                        alpha = FastOutSlowInEasing.transform(progress)
                    )
                }
            }
            .fillMaxWidth()
            .height(80.dp)
    ) {
        if (swipeRefreshState.isRefreshing) {
            LinearProgressIndicator(
                Modifier.fillMaxWidth(),
                color =Color.Gray
            )
        } else {
            val trigger = with(LocalDensity.current) { refreshTriggerDistance.toPx() }
            val progress = (swipeRefreshState.indicatorOffset / trigger).coerceIn(0f, 1f)
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color =Color.Gray
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        topAppKaspi()

        Spacer(modifier = Modifier.padding(6.dp))
        listItem(
            image = R.drawable.img,
            title = "Kaspi Gold",
            tint = "*1234",
            price = "100 000 453 23,90"
        )
        Spacer(modifier = Modifier.padding(10.dp))
        listDepo(
            image = R.drawable.img_2,
            title = "Открыть Kaspi Red"
        )
        Spacer(modifier = Modifier.padding(10.dp))



        listItem(
            image = R.drawable.img_1,
            title = "Депозит KZT",
            tint = "до 16.10.2025",
            price = "100 000 453 23,86"
        )
        Divider(
            modifier = Modifier.padding(start = 70.dp),
            color = Color.Gray,
            thickness = 0.5.dp
        )
        listDepo(
            image = R.drawable.img_2,
            title = "Открыть Kaspi Депозит"
        )
        Spacer(modifier = Modifier.padding(10.dp))

        listDepo(
            image = R.drawable.img_2,
            title = "Оформить кредит или рассрочку"
        )
        Spacer(modifier = Modifier.padding(10.dp))
        listDepo(
            image = R.drawable.img_3,
            title = "Kaspi Бонус",
            price = "300₸"
        )

    }
}


