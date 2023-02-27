package com.richmondprojects.acegameapp.data.remote.api

import com.richmondprojects.acegameapp.data.remote.dto.GamesDto
import com.richmondprojects.acegameapp.data.remote.dto.GameDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("games")
    suspend fun getAllGames(): List<GamesDto>

    @GET("game")
    suspend fun getGameDetail(
        @Query("id") id: Int
    ): GameDetailDto
}