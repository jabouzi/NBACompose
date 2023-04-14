package com.skanderjabouzi.nbacompose.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skanderjabouzi.nbacompose.app.theme.NBAComposeTheme
import com.skanderjabouzi.nbacompose.teams.presentation.TeamsListViewModel
import com.skanderjabouzi.nbacompose.ui.TeamsListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NbaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NbaApp() {
    NBAComposeTheme {
        Scaffold(
            topBar = {

            },

            content = {
                TeamsListScreen()
            }
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBAComposeTheme {
        Greeting("Android")
    }
}