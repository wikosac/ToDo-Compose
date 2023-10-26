package com.wikosac.todo_compose.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wikosac.todo_compose.data.models.Priority
import com.wikosac.todo_compose.ui.theme.Typography

@Composable
fun PriorityItem(priority: Priority) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            style = Typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.LOW)
}