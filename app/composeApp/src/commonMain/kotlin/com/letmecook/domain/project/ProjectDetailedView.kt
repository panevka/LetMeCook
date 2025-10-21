package com.letmecook.domain.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
internal fun ProjectDetailedView (project: ProjectEntity) {

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(project.name, style = MaterialTheme.typography.titleLarge)
            Text(project.description, style = MaterialTheme.typography.titleLarge)
            Text(project.technicalStack.joinToString(", "), style = MaterialTheme.typography.titleLarge)
            Text(project.paymentType.toString(), style = MaterialTheme.typography.titleLarge)
            project.tags.map {
                tag ->
                Text(tag, style = MaterialTheme.typography.titleLarge)
            }
        }
    }

}