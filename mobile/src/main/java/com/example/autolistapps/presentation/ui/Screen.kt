package com.example.autolistapps.presentation.ui

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main")
    data object DetailScreen : Screen("detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}