package com.wikosac.todo_compose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wikosac.todo_compose.util.Action
import com.wikosac.todo_compose.util.Constants
import com.wikosac.todo_compose.util.Constants.TASK_ARGUMENT_KEY
import com.wikosac.todo_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}