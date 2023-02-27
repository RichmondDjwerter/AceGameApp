package com.richmondprojects.acegameapp.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.richmondprojects.acegameapp.data.repository.RepositoryImplementation
import com.richmondprojects.acegameapp.domain.util.GAME_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImplementation,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var gameId: String

    init {
        savedStateHandle.get<String>(key = GAME_ID_KEY)?.let { id ->
            gameId = id
        }
    }
}