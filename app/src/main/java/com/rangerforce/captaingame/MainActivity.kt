package com.rangerforce.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rangerforce.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptainGame(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CaptainGame(modifier: Modifier) {
    val cardinalDirections = listOf("North", "East", "South", "West")
    var treasureFound by remember { mutableIntStateOf(0) }
    val direction = remember { mutableStateOf("North") }
    val stormOrTreasure = remember { mutableStateOf("Starting Journey") }

    fun foundTreasure(selectedDirection: String) {
        direction.value = selectedDirection
        if (Random.nextBoolean()) {
            treasureFound++
            stormOrTreasure.value = "Treasure Found!"
        } else {
            stormOrTreasure.value =  if(Random.nextBoolean()) "Storm encountered!" else "Keep sailing..."
        }
    }

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Treasure Found: $treasureFound")
        Text("Sailing Direction: ${direction.value}")
        Text(stormOrTreasure.value)
        cardinalDirections.forEach { direction ->
            Button(onClick = {
                foundTreasure(direction)
            }, modifier = Modifier.padding(8.dp)) {
                Text(text = "Sail $direction")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptainGameTheme {
        CaptainGame(modifier = Modifier.padding(16.dp))
    }
}