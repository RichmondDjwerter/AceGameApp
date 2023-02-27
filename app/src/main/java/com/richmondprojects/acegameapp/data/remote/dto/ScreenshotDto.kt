package com.richmondprojects.acegameapp.data.remote.dto

import com.richmondprojects.acegameapp.domain.model.Screenshot

data class ScreenshotDto(
    val id: Int,
    val image: String
) {
    fun toScreenshotDomain(): Screenshot {
        return Screenshot(
            id, image
        )
    }
}