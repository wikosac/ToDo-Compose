package com.wikosac.todo_compose.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wikosac.todo_compose.R
import com.wikosac.todo_compose.ui.viewmodels.SharedViewModel
import com.wikosac.todo_compose.util.Action
import com.wikosac.todo_compose.util.SearchAppBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit, sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true, block = {
        Log.d("ListScreen", "ListScreen: lauch effect triggered")
        sharedViewModel.getAllTasks()
    })

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    val action by sharedViewModel.action
    val snackbarHostState = remember { SnackbarHostState() }

    DisplaySnackbar(
        snackbarHostState = snackbarHostState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action) },
        onUndoClicked = { sharedViewModel.action.value = it },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ListAppBar(
                sharedViewModel,
                searchAppBarState,
                searchTextState
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                ListContent(
                    tasks = allTasks,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        }, containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun DisplaySnackbar(
    snackbarHostState: SnackbarHostState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseActions()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action, block = {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = setActionLabel(action)
                )
                undoDeletedTask(
                    action = action,
                    snackbarResult = snackbarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    })
}

private fun setActionLabel(action: Action): String {
    return if (action == Action.DELETE) "UNDO" else "OK"
}

private fun undoDeletedTask(
    action: Action,
    snackbarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackbarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}