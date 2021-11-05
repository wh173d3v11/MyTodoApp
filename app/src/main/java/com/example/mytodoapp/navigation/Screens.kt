package com.example.mytodoapp.navigation

import androidx.navigation.NavHostController
import com.example.mytodoapp.util.Action
import com.example.mytodoapp.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive = true}
        }
    }

    val task:(Int) -> Unit = { taskid ->
        navController.navigate("task/${taskid}")
    }
}