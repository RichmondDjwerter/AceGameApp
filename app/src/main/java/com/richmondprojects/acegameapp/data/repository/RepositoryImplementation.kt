package com.richmondprojects.acegameapp.data.repository

import com.richmondprojects.acegameapp.data.remote.api.Api
import com.richmondprojects.acegameapp.domain.model.GameDetails
import com.richmondprojects.acegameapp.domain.model.Games
import com.richmondprojects.acegameapp.domain.repository.Repository
import com.richmondprojects.acegameapp.domain.util.Resource
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val api: Api
) : Repository, BaseRepository() {
    override suspend fun getAllGames(): Resource<List<Games>> {
        val response = invokeApi {
            api.getAllGames()
        }
        return when (response) {
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.toGamesDomain() } ?: emptyList()
            )
            is Resource.Error -> Resource.Error(message = response.message)
            is Resource.Loading -> Resource.Loading()
        }
    }

    override suspend fun getGameDetail(id: Int): Resource<GameDetails?> {
        val response = invokeApi {
            api.getGameDetail(id)
        }
        return when (response) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(message = response.message)
            is Resource.Success -> Resource.Success(data = response.data?.toGameDetailsDomain())
        }
    }
}