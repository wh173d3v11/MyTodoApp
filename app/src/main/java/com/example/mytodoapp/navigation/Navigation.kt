package com.example.mytodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mytodoapp.navigation.destinations.listComposable
import com.example.mytodoapp.navigation.destinations.taskComposable
import com.example.mytodoapp.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {
    val screen = remember(navController) {
        //remember - saves backstack of composible screen throughout the application
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable(navigateToListScreen = screen.list)
    }
}