package com.example.mytodoapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.data.models.Priority
import com.example.mytodoapp.data.models.ToDoTask
import com.example.mytodoapp.data.repo.ToDoRepo
import com.example.mytodoapp.util.RequestState
import com.example.mytodoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repo: ToDoRepo) : ViewModel() {

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


    init {
        getAllTasks()
    }
}