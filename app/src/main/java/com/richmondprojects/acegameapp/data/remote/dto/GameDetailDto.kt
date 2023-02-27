package com.richmondprojects.acegameapp.data.remote.dto

import com.richmondprojects.acegameapp.domain.model.GameDetails

data class GameDetailDto(
    val description: String,
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val minimum_system_requirements: MinimumSystemRequirementsDto,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val screenshots: List<ScreenshotDto>,
    val short_description: String,
    val status: String,
    val thumbnail: String,
    val title: String
) {
    fun toGameDetailsDomain(): GameDetails {
        return GameDetails(
            description = description,
            developer = developer,
            freetogame_profile_url = freetogame_profile_url,
            game_url = game_url,
            genre = genre,
            id = id,
            minimum_system_requirements = minimum_system_requirements.toMinRequirementDomain(),
            platform = platform,
            publisher = publisher,
            release_date = release_date,
            screenshots = screenshots.map { it.toScreenshotDomain() },
            short_description = short_description,
            status = status,
            thumbnail = thumbnail,
            title = title
        )
    }
}