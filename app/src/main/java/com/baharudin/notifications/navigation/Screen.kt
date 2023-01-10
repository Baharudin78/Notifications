package com.baharudin.notifications.navigation

sealed class Screen(val route : String) {
    object Main : Screen(route = "main")
    object Detail : Screen(route = "details/$MY_ARGS}") {
        fun passArgument(message : String) = "details/$message"
    }
}
