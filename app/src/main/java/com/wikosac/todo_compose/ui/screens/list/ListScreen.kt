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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wikosac.todo_compose.R
import com.wikosac.todo_compose.ui.viewmodels.SharedViewModel
import com.wikosac.todo_compose.util.SearchAppBarState

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

    Scaffold(
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