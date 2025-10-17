package com.letmecook.domain.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun ProjectListView() {


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

       Column(
           modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
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