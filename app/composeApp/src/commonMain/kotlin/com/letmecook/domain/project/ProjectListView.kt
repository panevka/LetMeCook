package com.letmecook.domain.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.letmecook.app.client
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.HttpResponse
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.collections.mutableListOf

var projects = mutableStateListOf<ProjectEntity>();

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun ProjectListView() {

    var backendProjects by remember { mutableStateOf<List<ProjectDto>>(emptyList())}
    LaunchedEffect(true){
        try {
            val response: List<ProjectDto> = client.get(Projects.All()).body()
            backendProjects = response
        } catch (e: Exception){
            println("Error")
        }
    }

//    fun initMockData(): List<ProjectEntity> {
//        val tags = arrayOf("Open-source", "Startup", "Teenagers", "Non-tech tag");
//
//        val projects = (1..5).map { project ->
//            ProjectEntity(
//                "Project $project",
//                "Super-duper, hyper, gigachad, sigma description $project",
//                tags.map { tag -> "$tag $project" }.toTypedArray(),
//                PaymentType.values().random(), arrayOf(TechnicalStack.values().random())
//            )
//        }
//        return projects;
//    }

//    val mockProjects = initMockData();
//    if(projects.isEmpty()){
//        projects.addAll(mockProjects)
//    }

    var showDetailedProjectView by remember { mutableStateOf(false) }
//    var clickedProject: ProjectEntity? by remember { mutableStateOf(null) }
    var clickedProject: ProjectDto? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {

        clickedProject.let { project ->
            if (showDetailedProjectView && project != null) {
//                ProjectDetailedView(project)
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
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Text(project.id.toString())
                            Text(project.title)
//                            Text(
//                                project.name,
//                                style = MaterialTheme.typography.titleLarge,
//                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
//                            )
//                            Text("Technical stack: ${project.technicalStack.joinToString(", ")} ")
//                            Text("Payment: ${project.paymentType} ")

//                            FlowRow(
//                                modifier = Modifier.wrapContentSize(),
//                                horizontalArrangement = Arrangement.spacedBy(10.dp),
//                                verticalArrangement = Arrangement.spacedBy(5.dp)
//                            ) {
//                                project.tags.map { tag ->
//
//                                    Text(
//                                        text = tag,
//                                        color = Color.White,
//                                        modifier = Modifier.clip(
//                                            RoundedCornerShape(percent = 50)
//                                        ).background(Color.Blue).padding(5.dp),
//                                        style = MaterialTheme.typography.labelSmall
//                                    )
//
//                                }
//                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Button(onClick = {
                                    clickedProject = project
                                    showDetailedProjectView = true
                                }) {
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