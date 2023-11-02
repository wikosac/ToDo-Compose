package com.wikosac.todo_compose.ui.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wikosac.todo_compose.data.models.Priority
import com.wikosac.todo_compose.data.models.ToDoTask
import com.wikosac.todo_compose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                TaskContent(
                    title = "Task 5",
                    onTitleChange = {},
                    description = "-",
                    onDescriptionChange = {},
                    priority = Priority.HIGH,
                    onPrioritySelected = {}
                )
            }
        }
    )
}