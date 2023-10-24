package com.wikosac.todo_compose.data

import androidx.room.Database
import com.wikosac.todo_compose.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase {

    abstract fun toDoDao(): ToDoDao
}