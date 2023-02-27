package com.richmondprojects.acegameapp.domain.repository

import com.richmondprojects.acegameapp.domain.model.GameDetails
import com.richmondprojects.acegameapp.domain.model.Games
import com.richmondprojects.acegameapp.domain.util.Resource

interface Repository {
    suspend fun getAllGames(): Resource<List<Games>>

    suspend fun getGameDetail(id: Int): Resource<GameDetails?>
}