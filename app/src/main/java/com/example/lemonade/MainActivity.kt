package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.AppBarColour
import com.example.lemonade.ui.theme.ImageBackgroundColour
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var taps by remember { mutableIntStateOf(1) }
    var requiredTaps by remember { mutableIntStateOf((2..5).random()) }

    val pages = mapOf(
        1 to Pair(R.drawable.lemon_tree, R.string.lemon_select),
        2 to Pair(R.drawable.lemon_squeeze, R.string.lemon_squeeze),
        3 to Pair(R.drawable.lemon_drink, R.string.lemon_drink),
        4 to Pair(R.drawable.lemon_restart, R.string.lemon_restart),
    )

    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = AppBarColour,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            LemonadePage(image = pages.getValue(taps).first,
                imageDescription = pages.getValue(taps).second,
                onImageClick = {
                    if (taps == 2) {
                        if (requiredTaps > 1) {
                            requiredTaps--
                        } else {
                            taps++
                            requiredTaps = (2..5).random()
                        }
                    } else {
                        taps = if (taps < 4) taps + 1 else 1
                    }
                })
        }
    }
}

@Composable
fun LemonadePage(
    image: Int = R.drawable.lemon_tree,
    imageDescription: Int = R.string.lemon_select,
    onImageClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = image),
                contentDescription = stringResource(id = R.string.lemon_tree),
                modifier = Modifier
                    .padding(16.dp)
                    .size(150.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(
                        color = ImageBackgroundColour,
                    )
                    .clickable { onImageClick() })

            Text(
                text = stringResource(id = imageDescription), modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}