package com.wikosac.todo_compose.navigation

import androidx.navigation.NavHostController
import com.wikosac.todo_compose.util.Action
import com.wikosac.todo_compose.util.Constants.LIST_SCREEN

class Screens(navHostController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navHostController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }
}