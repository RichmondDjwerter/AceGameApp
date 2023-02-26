package com.richmondprojects.acegameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.richmondprojects.acegameapp.presentation.components.drawer.NavigationDrawer
import com.richmondprojects.acegameapp.presentation.components.drawer.NavigationDrawerItem
import com.richmondprojects.acegameapp.presentation.screens.base.Screens
import com.richmondprojects.acegameapp.presentation.screens.home.HomeViewModel
import com.richmondprojects.acegameapp.ui.theme.AceGameAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.splashScreen.value } }
        setContent {
            AceGameAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun Index(
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {
            NavigationDrawer(
                header = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_free_to_play_launcher),
                        contentDescription = "",
                        modifier = Modifier.size(size = dimensionResource(id = com.intuit.sdp.R.dimen._25sdp)),
                        contentScale = ContentScale.FillHeight,
                        alignment = Alignment.Center
                    )
                },
                content = {
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(16.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = R.drawable.ic_bars_staggered_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = R.string.lbl_home),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(16.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = R.drawable.ic_windows_brands),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = R.string.lbl_pc_games),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(16.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = R.drawable.ic_window_maximize_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = R.string.lbl_web_games),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(16.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = R.drawable.ic_arrow_trend_up_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = R.string.lbl_latest_games),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screens.HomeScreen.route
        ) {
            composable(Screens.HomeScreen.route){

            }
        }
    }
}
