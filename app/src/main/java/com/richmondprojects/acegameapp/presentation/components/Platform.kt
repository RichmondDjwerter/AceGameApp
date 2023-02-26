package com.richmondprojects.acegameapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.richmondprojects.acegameapp.R

@Composable
fun Platform(text: String) {

    val resource = if (text.contains("windows", ignoreCase = true)) {
        R.drawable.ic_windows_brands
    } else {
        R.drawable.ic_window_maximize_solid
    }

    Icon(
        painter = painterResource(id = resource),
        contentDescription = "Platform Icon",
        tint = MaterialTheme.colorScheme.primaryContainer
    )
}