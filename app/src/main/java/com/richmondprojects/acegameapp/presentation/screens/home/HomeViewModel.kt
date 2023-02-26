package com.richmondprojects.acegameapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richmondprojects.acegameapp.data.repository.RepositoryImplementation
import com.richmondprojects.acegameapp.domain.model.Games
import com.richmondprojects.acegameapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImplementation
) : ViewModel() {

    private val _splashScreenVisible = mutableStateOf(false)
    val splashScreen: State<Boolean>
        get() = _splashScreenVisible

    private val _games: MutableStateFlow<Resource<List<Games>>> =
        MutableStateFlow(Resource.Loading())
    val games: StateFlow<Resource<List<Games>>>
        get() = _games

    init {
        viewModelScope.launch {
            _splashScreenVisible.value = true
            _games.value = repositoryImpl.getAllGames()
            _splashScreenVisible.value = false
        }
    }
}