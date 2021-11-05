package com.example.mytodoapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen:(Action) -> Unit
){
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){

    }
}