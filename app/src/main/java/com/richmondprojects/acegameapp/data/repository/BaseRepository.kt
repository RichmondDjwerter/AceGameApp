package com.richmondprojects.acegameapp.data.repository

import com.richmondprojects.acegameapp.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> invokeApi(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Throwable) {
                Resource.Error(message = e)
            }
        }
    }
}