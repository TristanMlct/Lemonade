package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Header()
        Spacer(modifier = Modifier.weight(1f))
        Body()
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Lemonade",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(1) }
    val imageResource = when (state) {
        1 -> R.drawable.lemon_tree
        in (2..5) -> R.drawable.lemon_squeeze
        6 -> R.drawable.lemon_drink
        7 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val currentText = when (state) {
        1 -> R.string.lemon_tree
        in (2..5) -> R.string.lemon_squeeze
        6 -> R.string.lemon_drink
        7 -> R.string.lemon_empty_glass
        else -> 0
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ){
        Box(modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                when (state) {
                    1 -> state++
                    in (2..5) -> state += if (Math.random() < 0.5 || state == 5) 1 else 2
                    6 -> state++
                    7 -> state = 1
                }
            },
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
        }
        Text(
            text = stringResource(currentText),
            fontSize = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}