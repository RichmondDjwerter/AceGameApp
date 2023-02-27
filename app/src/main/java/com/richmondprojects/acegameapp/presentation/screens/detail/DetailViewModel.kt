package com.richmondprojects.acegameapp.presentation.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richmondprojects.acegameapp.data.repository.RepositoryImplementation
import com.richmondprojects.acegameapp.domain.model.GameDetails
import com.richmondprojects.acegameapp.domain.util.GAME_ID_KEY
import com.richmondprojects.acegameapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImplementation,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _gameDetailState: MutableStateFlow<Resource<GameDetails?>> =
        MutableStateFlow(value=Resource.Loading())
    val gameDetailState: StateFlow<Resource<GameDetails?>>
        get() = _gameDetailState

    private val _gameTitle = mutableStateOf("")
    val gameTitle: State<String>
        get() = _gameTitle

    init {
        savedStateHandle.get<String>(key = GAME_ID_KEY)?.let { id ->
            getGameDetails(id.toInt())
        }
    }

    private fun getGameDetails(id: Int) {
        viewModelScope.launch {
            _gameDetailState.value = repositoryImpl.getGameDetail(id)
        }
    }

    fun setGameTitle(title: String) {
        _gameTitle.value = title
    }
}