package com.letmecook.domain.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.letmecook.app.client
import com.letmecook.app.currentUserId
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ProjectDetailedView (project: ProjectDto) {

    val scope = rememberCoroutineScope()
    var joined by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(project.title, style = MaterialTheme.typography.titleLarge)
            Text(project.description, style = MaterialTheme.typography.titleLarge)
            Text(project.tech_stack.joinToString(", "), style = MaterialTheme.typography.titleLarge)
            Text(project.payment_type.toString(), style = MaterialTheme.typography.titleLarge)

            Button(onClick = {
                println("aha")
                scope.launch {
                    try {
                        val success = joinProject(client, project.id, currentUserId)
                        println("aha2: success = $success")
                        if (success) {
                            joined = true
                        }
                        println("aha3")
                    } catch (e: Exception) {
                        println("Join failed")
                        e.printStackTrace()
                    }
                }
            }) {
                Text(if (joined) "Joined" else "Join")
            }
        }
    }

}