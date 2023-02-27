package com.richmondprojects.acegameapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.richmondprojects.acegameapp.R
import com.richmondprojects.acegameapp.domain.model.Games

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    games: Games,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = games.thumbnail,
                onLoading = {
                    ConstraintLayout(modifier = Modifier.fillMaxSize(0.5f)) {
                        val indicatorRef = createRef()
                        CircularProgressIndicator(
                            modifier = modifier.constrainAs(indicatorRef) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                },
                contentScale = ContentScale.Crop,
                crossFade = 1000,
                modifier = Modifier.fillMaxHeight(0.3f),
                onError = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_warning),
                        contentDescription = "Error happened",
                        tint = MaterialTheme.colorScheme.error
                    )
                })
            Column(
                modifier = Modifier
                    .fillMaxWidth().fillMaxHeight()
                    .padding(3.dp), verticalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        text = games.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.95f)
                            .fillMaxHeight(fraction = 0.6f)
                    ) {
                        Text(
                            text = games.short_description,
                            modifier = Modifier.fillMaxWidth(fraction = 0.85f),
                            overflow = TextOverflow.Ellipsis, maxLines = 5
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp)
                ) {
                    AssistChip(
                        colors = AssistChipDefaults.assistChipColors(MaterialTheme.colorScheme.onBackground),
                        label = {
                            Text(
                                modifier = Modifier.padding(all = 5.dp),
                                text = games.genre,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }, onClick = {}, shape = MaterialTheme.shapes.extraLarge
                    )
                    Spacer(modifier = Modifier.padding(end = 3.dp))
                    Platform(text = games.platform)
                }
            }
        }
    }
}