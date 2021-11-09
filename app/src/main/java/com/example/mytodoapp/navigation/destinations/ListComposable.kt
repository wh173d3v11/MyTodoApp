package com.example.mytodoapp.navigation.destinations

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.example.mytodoapp.ui.screens.lists.ListScreen
import com.example.mytodoapp.ui.viewmodel.SharedViewModel
import com.example.mytodoapp.util.Constants.LIST_ARGUMENT_KEY
import com.example.mytodoapp.util.Constants.LIST_SCREEN
import com.example.mytodoapp.util.toAction


@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("TAG", "listComposable: action $action")

        LaunchedEffect(key1 = action, block = {
            sharedViewModel.action.value = action
        })

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}