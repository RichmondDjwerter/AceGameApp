package com.richmondprojects.acegameapp.presentation.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.richmondprojects.acegameapp.R
import com.richmondprojects.acegameapp.domain.util.TITLE
import com.richmondprojects.acegameapp.presentation.components.*

@Composable
fun GameDetail(
    viewModel: DetailViewModel,
    navController: NavController,
    onPlayTheGameClicked: (String) -> Unit
) {
    val gameDetailState by viewModel.gameDetailState.collectAsState()
    val gameTitleState by viewModel.gameTitle

    Column(modifier = Modifier.fillMaxSize()) {
        NavBar(title = stringResource(id = R.string.lbl_detail, gameTitleState),
            onBackPressed = { navController.navigateUp() })
        Spacer(modifier = Modifier.height(20.dp))
        StateHandler(
            state = gameDetailState,
            onLoading = { LoadingGameDetail(onLoading = { viewModel.setGameTitle(TITLE) }) },
            onFailure = { WarningMessage(textId = R.string.txt_empty_result) }
        ) { resource ->
            resource.data?.let { gameDetails ->
                val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp /
                        LocalDensity.current.density
                viewModel.setGameTitle(gameDetails.title)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()).padding(12.dp)
                ) {
                    if (gameDetails.screenshots.isEmpty()) {
                        NetworkImage(
                            url = gameDetails.thumbnail,
                            crossFade = 1000,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .requiredHeight(height = screenHeight * 0.6f)
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .clip(shape = MaterialTheme.shapes.large),
                            onLoading = {
                                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                                    val indicatorRef = createRef()
                                    CircularProgressIndicator(
                                        modifier = Modifier.constrainAs(indicatorRef) {
                                            top.linkTo(parent.top)
                                            bottom.linkTo(parent.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        })
                                }
                            },
                            onError = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_warning),
                                    contentDescription = "Warning Logo",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            })
                    } else {
                        CarouselView(
                            urls = gameDetails.screenshots.map { it.image },
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = screenHeight * 0.6f)
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            shape = MaterialTheme.shapes.medium, crossFade = 1000
                        )
                    }
                    Spacer(modifier = Modifier.height(height = 30.dp))
                    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                        Text(
                            text = stringResource(id = R.string.lbl_about, gameDetails.title),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        ExpandableText(
                            text = gameDetails.description,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(height = 30.dp))
                        Text(
                            text = stringResource(id = R.string.lbl_extra),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_title),
                            secondTitle = stringResource(id = R.string.lbl_developer),
                            textColor = MaterialTheme.colorScheme.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = gameDetails.title,
                            secondTitle = gameDetails.developer,
                            textColor = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_publisher),
                            secondTitle = stringResource(id = R.string.lbl_release_date),
                            textColor = MaterialTheme.colorScheme.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = gameDetails.publisher,
                            secondTitle = gameDetails.release_date,
                            textColor = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_genre),
                            secondTitle = stringResource(id = R.string.lbl_platform),
                            textColor = MaterialTheme.colorScheme.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = gameDetails.genre,
                            secondTitle = gameDetails.platform,
                            textColor = MaterialTheme.colorScheme.onBackground,
                            icon = {
                                Box(modifier = Modifier.padding(end = 5.dp)) {
                                    Platform(text = gameDetails.platform)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(height = 30.dp))
                        gameDetails.minimum_system_requirements.let { minimumSystemRequirements ->
                            Text(
                                text = stringResource(id = R.string.lbl_msr),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(height = 20.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_os),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = minimumSystemRequirements.os,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_memory),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = minimumSystemRequirements.memory,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_storage),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = minimumSystemRequirements.storage,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_graphics),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = minimumSystemRequirements.graphics,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(
                                    id = R.string.lbl_developer_copyright,
                                    gameDetails.developer
                                ),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(height = 30.dp))
                        }
                        LeadingIconButton(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            iconResId = R.drawable.ic_sign_in_alt_solid,
                            textButton = stringResource(id = R.string.lbl_play_the_game),
                            onClick = { onPlayTheGameClicked(gameDetails.game_url) })
                        Spacer(modifier = Modifier.height(height = 20.dp))
                    }
                }
            }
        }
    }
}