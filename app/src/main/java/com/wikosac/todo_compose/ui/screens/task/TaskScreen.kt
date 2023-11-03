package com.wikosac.todo_compose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context)
                        }
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = { newTitle ->
                        sharedViewModel.updateTitle(newTitle)
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

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields empty", Toast.LENGTH_SHORT).show()
}