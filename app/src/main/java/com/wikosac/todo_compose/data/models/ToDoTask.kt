package com.wikosac.todo_compose.data.models

import androidx.room.Entity
import com.wikosac.todo_compose.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: String,
)
