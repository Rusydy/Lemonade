package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
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
            Lemonade()
        }
    }
}

@Composable
fun Lemonade() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.lemon_tree),
                contentDescription = stringResource(id = R.string.lemon_tree),
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = Color(0xFFCBEBD7), shape = MaterialTheme.shapes.large
                    )
            )

            Text(
                text = stringResource(id = R.string.lemon_select),
                modifier = Modifier.padding(16.dp)
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