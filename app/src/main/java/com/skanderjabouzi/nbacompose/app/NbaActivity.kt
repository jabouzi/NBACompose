package com.skanderjabouzi.nbacompose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.skanderjabouzi.nbacompose.app.theme.NBAComposeTheme
import com.skanderjabouzi.nbacompose.navigation.NbaNavHost

class NbaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaApp()
        }
    }
}

@Composable
fun NbaApp(
    modifier: Modifier = Modifier
) {
    NBAComposeTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
            }
        ) { innerPadding ->
            NbaNavHost(
                navController = navController,
                modifier = modifier.padding(innerPadding),
            )
        }
    }
}
