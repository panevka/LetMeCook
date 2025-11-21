package com.letmecook.domain.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.letmecook.app.AnimatedFogGradientBackground
import com.letmecook.app.AppColors
import com.letmecook.app.MinimalGlowBackground
import com.letmecook.app.TagChip
import com.letmecook.app.client
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import org.jetbrains.compose.ui.tooling.preview.Preview

var projects = mutableStateListOf<ProjectEntity>();

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun ProjectListView() {

//    var backendProjects by remember { mutableStateOf<List<ProjectDto>>(emptyList())}
    var backendProjects = Mocks.getAllProjects()
    LaunchedEffect(true){
        try {
            val response: List<ProjectDto> = client.get(Projects.All()).body()
            backendProjects = response
        } catch (e: Exception){
            println("Error")
        }
    }

    var showDetailedProjectView by remember { mutableStateOf(false) }
    var clickedProject: ProjectDto? by remember { mutableStateOf(null) }

    Box(Modifier.fillMaxSize()) {

        MinimalGlowBackground(
            modifier = Modifier.matchParentSize()
        )

    Column(
        modifier = Modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {

        Text(
            "Hot projects",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start),
            color = AppColors.SecondaryFontColor
        )

        clickedProject.let { project ->
            if (showDetailedProjectView && project != null) {
                ProjectDetailedView(project)
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            backendProjects.let {
            if(it == null ){
               item {Text("No projects")}
            } else {
                items(it) { project ->

                    ElevatedCard(
                        modifier = Modifier.height(200.dp).fillMaxWidth().clip(RoundedCornerShape(percent = 10))
                            .background(AppColors.Primary),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = AppColors.Primary
                        ),
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Text(
                                project.title,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                                color = AppColors.SecondaryFontColor
                            )

                            Text(project.description, color = AppColors.PrimaryFontColor, maxLines = 2, overflow = TextOverflow.Ellipsis)

                            FlowRow(){
                                project.tech_stack.map { unit ->
                                    TagChip(unit.toString())
                                }

                            }
                            Text("Payment: ${project.payment_type} ")

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Button(onClick = {
                                    clickedProject = project
                                    showDetailedProjectView = true
                                }) {
                                    Text(
                                        "Show details",
                                        style = MaterialTheme.typography.labelSmall,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                    }

                }
            }
            }

        }

    }

    }
}