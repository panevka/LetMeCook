package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.composeapp.generated.resources.Res
import app.composeapp.generated.resources.allDrawableResources
import app.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.letmecook.domain.project.PaymentType
import com.letmecook.domain.project.ProjectDetailedView
import com.letmecook.domain.project.ProjectEntity
import com.letmecook.domain.project.TechnicalStack
import kotlin.random.Random

@Composable
@Preview
fun App() {

    fun initMockData(): List<ProjectEntity> {
        val tags = arrayOf("Open-source", "Startup", "Teenagers", "Non-tech tag");

        val projects = (1..5).map { project ->
            ProjectEntity(
                "Project $project",
                "Super-duper, hyper, gigachad, sigma description $project",
                tags.map { tag -> "$tag $project" }.toTypedArray(),
                PaymentType.values().random(), TechnicalStack.values().random()
            )
        }
        return projects;
    }

    val mockProjects = initMockData();

    var showDetailedProjectView by remember {mutableStateOf(false)}
    var clickedProject: ProjectEntity? by remember {mutableStateOf(null)}

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Button(onClick = { showDetailedProjectView = false }) {
                            Text("Home")
                        }
                    },
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer).safeContentPadding()
                    .padding(innerPadding)
                    .fillMaxWidth(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                clickedProject.let { project ->
                    if (showDetailedProjectView && project != null) {
                        ProjectDetailedView(project)
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(mockProjects) { project ->

                        ElevatedCard(
                            modifier = Modifier.height(200.dp).fillMaxWidth().clip(RoundedCornerShape(percent = 10))
                                .background(Color.White)
                        ) {
                            Column(
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Text(
                                    project.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
                                )
                                Text("Technical stack: ${project.technicalStack} ")
                                Text("Payment: ${project.paymentType} ")

                                FlowRow(
                                    modifier = Modifier.wrapContentSize(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    project.tags.map { tag ->

                                        Text(
                                            text = tag,
                                            color = Color.White,
                                            modifier = Modifier.clip(
                                                RoundedCornerShape(percent = 50)
                                            ).background(Color.Blue).padding(5.dp),
                                            style = MaterialTheme.typography.labelSmall
                                        )

                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    Button(onClick = {
                                        clickedProject = project
                                        showDetailedProjectView = true
                                    }  ) {
                                        Text(
                                            "Join",
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