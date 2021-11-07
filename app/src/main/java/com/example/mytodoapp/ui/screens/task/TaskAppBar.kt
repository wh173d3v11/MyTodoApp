package com.example.mytodoapp.ui.screens.task

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytodoapp.ui.theme.topAppBarBackgroundColor
import com.example.mytodoapp.ui.theme.topAppBarContentColor
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.R
import com.example.mytodoapp.data.models.ToDoTask
import com.example.mytodoapp.ui.theme.TOP_APP_BAR_ICON_SIZE
import com.example.mytodoapp.ui.theme.TOP_APP_BAR_ICON_SIZE_20
import com.example.mytodoapp.util.DummyToDoTask

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
    }

}

@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { BackAction(onBackClicked = navigateToListScreen) },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_new_task),
                color = MaterialTheme.colors.topAppBarContentColor,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            modifier = Modifier.size(TOP_APP_BAR_ICON_SIZE_20),
            painter = painterResource(id = R.drawable.ic_stylish_arrowback),
            contentDescription = stringResource(id = R.string.back_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            modifier = Modifier.size(TOP_APP_BAR_ICON_SIZE),
            imageVector = Icons.Filled.Done,
            contentDescription = stringResource(id = R.string.add_new_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(selectedTask: ToDoTask, navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { CloseAction(onCloseClicked = navigateToListScreen) },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            DeleteAction(onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }
    )
}


@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            modifier = Modifier.size(TOP_APP_BAR_ICON_SIZE_20),
            painter = painterResource(id = R.drawable.ic_stylish_close),
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            modifier = Modifier.size(TOP_APP_BAR_ICON_SIZE),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            modifier = Modifier.size(TOP_APP_BAR_ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_update),
            contentDescription = stringResource(id = R.string.update_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Preview
@Composable
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Preview
@Composable
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(DummyToDoTask, navigateToListScreen = {})
}