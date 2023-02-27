package com.richmondprojects.acegameapp.presentation.screens.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.intuit.sdp.R
import com.richmondprojects.acegameapp.domain.model.Games
import com.richmondprojects.acegameapp.domain.util.Resource
import com.richmondprojects.acegameapp.presentation.components.drawer.NavigationDrawer
import com.richmondprojects.acegameapp.presentation.components.drawer.NavigationDrawerItem
import com.richmondprojects.acegameapp.presentation.screens.detail.DetailViewModel
import com.richmondprojects.acegameapp.presentation.screens.detail.GameDetail
import com.richmondprojects.acegameapp.presentation.screens.home.HomeScreen

@Composable
fun Index(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    availableGames: Resource<List<Games>>,
    onOpenDrawer: () -> Unit,
    onSearchClicked: () -> Unit,
    onGameClicked: (Int) -> Unit,
    onPlayTheGameClicked: (String) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {
            NavigationDrawer(
                header = {
                    Box(
                        modifier = Modifier
                            .size(size = dimensionResource(id = R.dimen._250sdp))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = com.richmondprojects.acegameapp.R.drawable.perfect),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.FillHeight,
                            alignment = Alignment.Center,
                        )
                    }

                },
                content = {
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(5.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = com.richmondprojects.acegameapp.R.drawable.ic_bars_staggered_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = com.richmondprojects.acegameapp.R.string.lbl_home),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(5.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = com.richmondprojects.acegameapp.R.drawable.ic_windows_brands),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = com.richmondprojects.acegameapp.R.string.lbl_pc_games),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(5.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = com.richmondprojects.acegameapp.R.drawable.ic_window_maximize_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = com.richmondprojects.acegameapp.R.string.lbl_web_games),
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textStyle = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(5.dp),
                        onClick = { /*TODO*/ },
                        iconPainter = painterResource(id = com.richmondprojects.acegameapp.R.drawable.ic_arrow_trend_up_solid),
                        iconColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(id = com.richmondprojects.acegameapp.R.string.lbl_latest_games),
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
            composable(Screens.HomeScreen.route) {
                HomeScreen(
                    onOpenDrawer = { onOpenDrawer() },
                    onSearchOpen = { onSearchClicked() },
                    availableGames = availableGames,
                    onGameClicked = { gameId ->
                        onGameClicked(gameId)
                    }
                )
            }
            composable(Screens.DetailScreen.route) {
                val viewModel = hiltViewModel<DetailViewModel>()
                GameDetail(
                    viewModel, navController,
                    onPlayTheGameClicked = { gameUrl ->
                        onPlayTheGameClicked(gameUrl)
                    })
            }
        }
    }
}