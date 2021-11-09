package com.example.mytodoapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.data.models.Priority
import com.example.mytodoapp.data.models.ToDoTask
import com.example.mytodoapp.data.repo.ToDoRepo
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.Constants.MAX_DESCRIPTION_LENGTH
import com.example.mytodoapp.util.Constants.MAX_TITLE_LENGTH
import com.example.mytodoapp.util.RequestState
import com.example.mytodoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repo: ToDoRepo) : ViewModel() {
    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)
    val id: MutableState<Int> = mutableStateOf(-1)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    //list screen - HOmeScreen
    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    //Selected task Screen
    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask


    private fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repo.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value, priority = priority.value
            )
            repo.addTask(toDoTask = toDoTask)
        }
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value, priority = priority.value
            )
            repo.updateTask(toDoTask = toDoTask)
        }
    }


    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value, priority = priority.value
            )
            repo.deleteTask(toDoTask = toDoTask)
        }
    }

    fun handleDBActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {
                updateTask()
            }
            Action.DELETE -> {
                deleteTask()
            }
            Action.DELETE_ALL -> {

            }
            Action.UNDO -> {

            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    fun getSelectedTask(taskId: Int?) {
        if (taskId == -1 || taskId == null) {
            _selectedTask.value = null
            return
        }
        viewModelScope.launch {
            repo.getSelectedTask(taskId = taskId ?: -1).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    fun updateTaskField(selectedTask: ToDoTask?) {
        id.value = selectedTask?.id ?: -1
        title.value = selectedTask?.title ?: ""
        description.value = selectedTask?.description ?: ""
        priority.value = selectedTask?.priority ?: Priority.LOW
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) title.value = newTitle
    }

    fun updateDescription(it: String) {
        if (it.length < MAX_DESCRIPTION_LENGTH) description.value = it
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }


    init {
        getAllTasks()
    }
}