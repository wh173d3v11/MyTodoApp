package com.example.mytodoapp.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.mytodoapp.data.models.Priority
import com.example.mytodoapp.data.models.ToDoTask
import com.example.mytodoapp.ui.viewmodel.SharedViewModel
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.DummyToDoTask

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->

                    if (action == Action.NO_ACTION || sharedViewModel.validateFields()) {
                        navigateToListScreen(
                            action
                        )
                    } else displayToast(context = context)

                })
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.updateDescription(it)
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields Are Empty..", Toast.LENGTH_SHORT).show()
}
