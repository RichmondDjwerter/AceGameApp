package com.richmondprojects.acegameapp.presentation.components.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconPainter: Painter,
    contentDescription: String = "",
    iconColor: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle
) {
    Row(verticalAlignment = CenterVertically,
        modifier = modifier.clickable { onClick() }) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.padding(3.dp)
        )
        Text(text = text, style = textStyle, color = textColor, fontWeight = FontWeight.Bold)
    }
}