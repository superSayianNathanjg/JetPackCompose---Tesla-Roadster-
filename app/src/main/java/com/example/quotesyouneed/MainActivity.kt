package com.example.quotesyouneed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quotesyouneed.ui.theme.QuotesYouNeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    QuotesYouNeedTheme {
        Surface(color = Color.Red) { // Applies this colour scheme to the entire app.
            MyScreenContent()
        }
    }
}

// isSelected state, initialised with 'remember' to false, add click handler to toggle state.
@Composable
fun Greeting(name: String, isSelected: Boolean, updateState: (Boolean) -> Unit) {
    // Logic for colour change on click.
    val bool: Boolean
    bool = if(isSelected) isSelected else false

    val backgroundColor by animateColorAsState(if (isSelected) Color.White else Color.Transparent)
    // Logic for observing click.
    Text(text = "Hello There! $name!",
         modifier = Modifier
                    .padding(24.dp)
                    .background(color = backgroundColor)
                    .clickable(onClick = { updateState(!isSelected) }),style = MaterialTheme.typography.body1)
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    var isSelected by remember { mutableStateOf(false)}

    LazyColumn(modifier = modifier
        .padding(16.dp)
        .background(MaterialTheme.colors.surface)) { // Passing in modifier.
        items(items = names) { name -> // For each name in input String list.
            Greeting(name = name, isSelected = isSelected, updateState = { newState -> isSelected = newState })
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "#$it" }) {
    val counterState = remember { mutableStateOf(0)}
    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) { // Making it flexible. Stretching out screen.
            NameList(names)
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount }
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count+1) },
        Modifier.padding(12.dp),
        colors = ButtonDefaults.buttonColors(  // Changes color of button after (x) amount of clicks.
            backgroundColor = if (count > 5 ) Color.Yellow else Color.White)) {
        Text("I've been clicked $count times")
    }
}

