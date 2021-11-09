package com.example.mytodoapp.navigation.destinations

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.example.mytodoapp.ui.screens.task.TaskScreen
import com.example.mytodoapp.ui.viewmodel.SharedViewModel
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.Constants
import com.example.mytodoapp.util.Constants.TASK_ARGUMENT_KEY

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY)
//        if (taskId != null && taskId != -1) {
//            sharedViewModel.getSelectedTask(taskId = taskId)
//        }
        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState() // by will change STATE to Original Format.

        LaunchedEffect(key1 = selectedTask, block = {
            Log.d("+++++", "taskComposable: LaunchedEffect Triggered. ")
            sharedViewModel.updateTaskField(selectedTask = selectedTask)
        })

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}