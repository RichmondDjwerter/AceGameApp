package com.richmondprojects.acegameapp.data.remote.dto

import com.richmondprojects.acegameapp.domain.model.Games

data class GamesDto(
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val short_description: String,
    val thumbnail: String,
    val title: String
) {
    fun toGamesDomain(): Games {
        return Games(
            developer = developer,
            freetogame_profile_url = freetogame_profile_url,
            game_url = game_url,
            genre = genre,
            id = id,
            platform = platform,
            publisher = publisher,
            release_date = release_date,
            short_description = short_description,
            thumbnail = thumbnail,
            title = title
        )
    }
}