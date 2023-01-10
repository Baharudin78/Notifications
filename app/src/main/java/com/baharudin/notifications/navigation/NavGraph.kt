package com.baharudin.notifications.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baharudin.notifications.screen.DetailScreen
import com.baharudin.notifications.screen.MainScreen

const val MY_URI = "https://notification-app.com"
const val MY_ARGS = "message"

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination =  Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            //
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(MY_ARGS){type = NavType.StringType}),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARGS={$MY_ARGS}" })
        ){
            val arguments = it.arguments
            arguments?.getString(MY_ARGS)?.let { message ->
                //
                DetailScreen(message = message)
            }
        }
    }
}