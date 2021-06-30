package com.example.quotesyouneed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotesyouneed.ui.theme.QuotesYouNeedTheme
import androidx.compose.ui.res.painterResource
// Testing Glide
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import com.google.accompanist.glide.rememberGlidePainter
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ImageList()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    QuotesYouNeedTheme {
        Surface { // Applies this colour scheme to the entire app.
            ImageList()
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
         Image(painter = rememberGlidePainter(request = "https://st.automobilemag.com/uploads/sites/5/2018/09/2020-Tesla-Roadster-white-on-Grand-Basel-show-floor.jpg"), contentDescription = "Boobs", modifier = Modifier.size(50.dp))
//        Image(painter = painterResource(id = R.drawable.tesla), contentDescription = "White Tesla", modifier = Modifier.size(50.dp))
        Spacer(Modifier.width(10.dp))
        Text("Tesla #$index", style = MaterialTheme.typography.subtitle1)

    }
}

@Composable
fun ImageList() {
    val scrollState = rememberLazyListState()
    val listSize = 50
    val coroutineScope = rememberCoroutineScope() // Save C scope where animated scroll is executed.
    Column() {
        Row(modifier = Modifier.padding(12.dp)) {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Top")
            }
            Spacer(Modifier.width(10.dp))
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize-1)
                }
            }) {
                Text("Bottom")
            }

        }

        LazyColumn(state = scrollState) {
            items(50) {
                ImageListItem(it)
            }
        }
    }
}

fun random() {

}
// Can only measure your children once.
fun Modifier.firstBaselineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints) // Measure composable.
        // Check for composables first base line.
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height including padding of composable first baseline.
        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // Position on screen. Have to use placeRelative or it won't be shown.
            placeable.placeRelative(0, placeableY)
        }
    }
)
