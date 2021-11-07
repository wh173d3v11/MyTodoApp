package com.example.mytodoapp.util

import com.example.mytodoapp.data.models.Priority
import com.example.mytodoapp.data.models.ToDoTask


var DummyToDoTask = ToDoTask(
    id = 0,
    title = "Rakida Rakida Voo",
    description = "Hey enna vena nadakattum Naan sandhoshama irupaen Usuru irukku verenna venum Ullaasama irupaen Edhaa punch-ah pottu udu maapla!",
    priority = Priority.MEDIUM
)

var DummyToDoTaskList = RequestState.Success(
    listOf(
        ToDoTask(
            id = 0,
            title = "Rakida Rakida Voo",
            description = "Hey enna vena nadakattum Naan sandhoshama irupaen Usuru irukku verenna venum Ullaasama irupaen Edhaa punch-ah pottu udu maapla!",
            priority = Priority.MEDIUM
        ), ToDoTask(
            id = 1,
            title = "Rakida Rakida Voo",
            description = "Hey enna vena nadakattum Naan sandhoshama irupaen Usuru irukku verenna venum Ullaasama irupaen Edhaa punch-ah pottu udu maapla!",
            priority = Priority.LOW
        ),
        ToDoTask(
            id = 2,
            title = "Rakida Rakida Voo",
            description = "Hey enna vena nadakattum Naan sandhoshama irupaen Usuru irukku verenna venum Ullaasama irupaen Edhaa punch-ah pottu udu maapla!",
            priority = Priority.HIGH
        )
    )
)