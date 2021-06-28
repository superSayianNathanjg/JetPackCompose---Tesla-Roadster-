package com.example.quotesyouneed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quotesyouneed.ui.theme.QuotesYouNeedTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                NewsStory()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    QuotesYouNeedTheme {
        Surface(color = Color.Red) { // Applies this colour scheme to the entire app.
            content()
            NewsStory()
        }
    }
}

@Composable
fun NewsStory() {
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(R.drawable.tesla),
                contentDescription = "A picture",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))
            Text("A 2020 Tesla Roadster",
                style = typography.h6)
            Divider(color = Color.White)
            Text("Absolutely Beautiful",
                style = typography.body2)
            Divider(color = Color.Transparent, thickness = 32.dp)
    }

        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount }
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count+1) }, Modifier.padding(12.dp)) {
        Text("I've been clicked $count times")
    }

}


