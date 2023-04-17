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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.skanderjabouzi.nbacompose.app.theme.NBAComposeTheme
import com.skanderjabouzi.nbacompose.navigation.AppDestination
import com.skanderjabouzi.nbacompose.navigation.TeamsListDestination
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
        val currentScreen: AppDestination by remember { mutableStateOf(TeamsListDestination) }
        val navController = rememberNavController()
        Scaffold(
            topBar = {

            },

            content = {
                currentScreen.screen()
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