package com.wikosac.todo_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGrey = Color(0xFFFCFCFC)
val MediumGrey = Color(0xFF9C9C9C)
val DarkGrey = Color(0xFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFFFFFFFF)

val topAppBarContentColor: Color
@Composable
get() = if (isSystemInDarkTheme()) LightGrey else MaterialTheme.colorScheme.primary

val topAppBarBackgroundColor: Color
@Composable
get() = if (isSystemInDarkTheme()) DarkGrey else MaterialTheme.colorScheme.primaryContainer

val taskItemBgColor: Color
@Composable
get() = if (isSystemInDarkTheme()) DarkGrey else Color.White

val taskItemTextColor: Color
@Composable
get() = if (isSystemInDarkTheme()) LightGrey else DarkGrey