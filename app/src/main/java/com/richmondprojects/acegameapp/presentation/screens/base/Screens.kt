package com.richmondprojects.acegameapp.presentation.screens.base

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object DetailScreen : Screens("detail_screen/{id}")
}
