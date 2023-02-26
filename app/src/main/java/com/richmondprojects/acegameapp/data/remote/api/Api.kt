package com.richmondprojects.acegameapp.data.remote.api

import com.richmondprojects.acegameapp.data.remote.dto.GamesDto
import retrofit2.http.GET

interface Api {

    @GET("games")
    suspend fun getAllGames(): List<GamesDto>
}