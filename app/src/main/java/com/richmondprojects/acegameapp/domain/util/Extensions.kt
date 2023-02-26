package com.richmondprojects.acegameapp.domain.util

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import com.richmondprojects.acegameapp.domain.model.Games

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(
        span = { GridItemSpan(maxLineSpan) },
        content = content
    )
}

fun List<Games>.getUrls(): List<String> {
    return takeRandomElement(3).map { it.thumbnail }
}

fun <T> List<T>.takeRandomElement(numberOfElements: Int): List<T> {
    return if (numberOfElements > size) this
    else asSequence().shuffled().take(numberOfElements).toList()
}