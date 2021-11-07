package com.example.mytodoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.mytodoapp.navigation.destinations.listComposable
import com.example.mytodoapp.navigation.destinations.taskComposable
import com.example.mytodoapp.ui.viewmodel.SharedViewModel
import com.example.mytodoapp.util.Constants.LIST_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) { //remember - saves backstack of composable screen throughout the application
        Screens(navController = navController)
    }
    AnimatedNavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}