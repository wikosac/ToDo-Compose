package com.wikosac.todo_compose.ui.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.wikosac.todo_compose.data.models.Priority
import com.wikosac.todo_compose.data.models.ToDoTask
import com.wikosac.todo_compose.ui.viewmodels.SharedViewModel
import com.wikosac.todo_compose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

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
                    title = title,
                    onTitleChange = { newTitle ->
                        sharedViewModel.title.value = newTitle
                    },
                    description = description,
                    onDescriptionChange = { newDesc ->
                        sharedViewModel.description.value = newDesc
                    },
                    priority = priority,
                    onPrioritySelected = { newPriority ->
                        sharedViewModel.priority.value = newPriority
                    }
                )
            }
        }
    )
}