package com.example.mytodoapp.ui.screens.lists

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytodoapp.R
import com.example.mytodoapp.ui.theme.fabBackgroundContentColor
import com.example.mytodoapp.ui.viewmodel.SharedViewModel
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.SearchAppBarState
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true, block = {
        Log.d("+++++", "ListScreen: LaunchedEffect Triggered")
    })

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDbActions = { sharedViewModel.handleDBActions(action = action) },
        taskTitle = sharedViewModel.title.value,
        action = action
    )
    Scaffold(scaffoldState = scaffoldState, //without this we will not able to see snackbar.
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundContentColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
//    ListScreen(navigateToTaskScreen = {},
//    sharedViewModel = )
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDbActions: () -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDbActions()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action, block = {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name} : $taskTitle", actionLabel = "OK"
                )

            }
        }
    })
}