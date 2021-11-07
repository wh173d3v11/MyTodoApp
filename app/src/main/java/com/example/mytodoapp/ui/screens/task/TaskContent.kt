package com.example.mytodoapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytodoapp.data.models.Priority
import com.example.mytodoapp.ui.theme.LARGE_PADDING
import com.example.mytodoapp.R
import com.example.mytodoapp.componenet.PriorityDropDown
import com.example.mytodoapp.ui.theme.SMALL_PADDING
import com.example.mytodoapp.util.DummyToDoTask

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxSize()
            .padding(all = LARGE_PADDING),
        verticalArrangement = Arrangement.spacedBy(LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(id = R.string.title)) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelected)
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = stringResource(id = R.string.description)) },
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Composable
@Preview
private fun TaskContentPreview() {
    TaskContent(
        title = DummyToDoTask.title,
        onTitleChange = {},
        description = DummyToDoTask.description,
        onDescriptionChange = {},
        priority = DummyToDoTask.priority,
        onPrioritySelected = {}
    )
}