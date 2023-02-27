package com.richmondprojects.acegameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.richmondprojects.acegameapp.presentation.screens.base.Index
import com.richmondprojects.acegameapp.presentation.screens.home.HomeViewModel
import com.richmondprojects.acegameapp.ui.theme.AceGameAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.splashScreen.value } }
        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val availableGames by viewModel.games.collectAsState()
            val scope = rememberCoroutineScope()
            AceGameAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Index(
                        scaffoldState = scaffoldState,
                        navController = navController,
                        availableGames = availableGames,
                        onGameClicked = { gameId ->
                            navController.navigate("detail_screen/$gameId")
                        },
                        onOpenDrawer = {
                            scope.launch { scaffoldState.drawerState.open() }
                        },
                        onSearchClicked = {}
                    )
                }
            }
        }
    }
}
