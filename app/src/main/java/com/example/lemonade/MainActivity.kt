package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithButtonAndImage(modifier: Modifier = Modifier) {
    var iconNum by remember { mutableStateOf(0) }
    val imageResource = when (iconNum % 4) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val strNum = when (iconNum % 4) {
        0 -> R.string.select
        1 -> R.string.squeeze
        2 -> R.string.drink
        else -> R.string.restart
    }
    var checkSqueeze by remember { mutableStateOf(0) }
    var finishSqueeze by remember { mutableStateOf((2..4).random()) }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(iconNum % 4 == 1) {
            if(finishSqueeze - checkSqueeze == 1) {
                Button(onClick = {
                    iconNum++
                    checkSqueeze = 0
                    finishSqueeze = (2..4).random()
                }) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = 1.toString()
                    )
                }
            } else {
                Button(onClick = { checkSqueeze++ }) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = 1.toString()
                    )
                }
            }

        } else {
            Button(onClick = { iconNum++ }) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = iconNum.toString()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(id = strNum))
    }
}