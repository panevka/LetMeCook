package com.letmecook.domain.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.composeapp.generated.resources.Res
import app.composeapp.generated.resources.project_icon
import com.letmecook.app.AppColors
import com.letmecook.app.TagChip
import com.letmecook.app.client
import com.letmecook.app.currentUserId
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
internal fun ProjectDetailedView (project: ProjectDto = Mocks.projectList[0]) {

    val scope = rememberCoroutineScope()
    var joined by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }


    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(AppColors.Gray).padding(20.dp).verticalScroll(
                rememberScrollState()
            )
        ) {
        Row(modifier = Modifier.padding(bottom = 10.dp ), verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(Res.drawable.project_icon),
                contentDescription = "aha",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(50.dp)
                    .padding(0.dp, 0.dp, 20.dp, 0.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column() {
                Text(
                    project.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = AppColors.SecondaryFontColor
                )

                Text("by ${project.author_username}", color = AppColors.PrimaryFontColor, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
            FlowRow(
                modifier = Modifier.padding( bottom = 10.dp, top=10.dp)
            ){
                project.tech_stack.map { unit ->
                    TagChip(unit.toString())
                }

            }
            Text("About project", style = MaterialTheme.typography.headlineLarge, color = Color.White)

            Box(modifier = Modifier.background(AppColors.LightGray, shape = RoundedCornerShape(20.dp)).clip(shape = RoundedCornerShape(20.dp)).padding(30.dp)

            ){
            Text(project.description, style = MaterialTheme.typography.titleLarge, color = AppColors.PrimaryFontColor)

            }

            Text("Why do you want to join?", style = MaterialTheme.typography.headlineLarge, color = Color.White)

            TextField(
                modifier = Modifier.fillMaxWidth().background(color = AppColors.LightGray, shape = RoundedCornerShape(20.dp)).clip(shape = RoundedCornerShape(20.dp)).height(400.dp),
                value = textInput,
                onValueChange = { textInput = it },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = AppColors.LightGray, focusedContainerColor = AppColors.LightGray, focusedIndicatorColor = AppColors.Pink, unfocusedTextColor = AppColors.PrimaryFontColor, focusedTextColor = AppColors.PrimaryFontColor )
            )

            Button(onClick = {
                scope.launch {
                    try {
                        val success = joinProject(client, project.id, currentUserId)
                        println("success = $success")
                        if (success) {
                            joined = true
                        }
                    } catch (e: Exception) {
                        println("Join failed")
                        e.printStackTrace()
                    }
                }
            },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Pink),
                modifier = Modifier.fillMaxWidth().height(50.dp).padding(top=10.dp)

                ) {
                Text(if (joined) "Joined" else "Join")
            }
        }
    }

}