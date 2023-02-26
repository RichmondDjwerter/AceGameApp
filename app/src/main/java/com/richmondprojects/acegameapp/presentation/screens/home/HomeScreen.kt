package com.richmondprojects.acegameapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.richmondprojects.acegameapp.R
import com.richmondprojects.acegameapp.domain.model.Games
import com.richmondprojects.acegameapp.domain.util.Resource
import com.richmondprojects.acegameapp.domain.util.getUrls
import com.richmondprojects.acegameapp.domain.util.header
import com.richmondprojects.acegameapp.presentation.components.CarouselView
import com.richmondprojects.acegameapp.presentation.components.GameCard

@Composable
fun HomeScreen(
    onOpenDrawer: () -> Unit,
    onSearchOpen: () -> Unit,
    availableGames: Resource<List<Games>>,
    onGameClicked: () -> Unit
) {
    availableGames.data?.let { games ->
        if (games.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_circle_info_solid),
                    contentDescription = "Error Icon", tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(id = R.string.txt_empty_result),
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { onOpenDrawer() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Drawable Icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = { onSearchOpen() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_magnifying_glass_solid),
                            contentDescription = "Search Icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    header {
                        CarouselView(
                            modifier = Modifier
                                .requiredHeight(height = 200.dp)
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 12.dp),
                            urls = games.getUrls(),
                            shape = MaterialTheme.shapes.large,
                            crossFade = 1000
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    items(items = games) {game ->
                        GameCard(games = game,
                            onClick = { onGameClicked() })
                    }
                }
            }
        }

    }

}