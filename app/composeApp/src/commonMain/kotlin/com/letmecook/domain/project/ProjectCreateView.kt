package com.letmecook.domain.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.letmecook.app.MinimalGlowBackground
import com.letmecook.app.PostList
import com.letmecook.app.client
import com.letmecook.app.currentUserId
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun ProjectCreateView(navController: NavController) {

    val scope = rememberCoroutineScope()

    var expandedDropdown by remember { mutableStateOf(false) }

    var newProjectTitle by remember { mutableStateOf("") }
    var newProjectPayment: PaymentType by remember { mutableStateOf(PaymentType.GRATITUDE) }
    var newProjectDescription by remember { mutableStateOf("") }
    var newProjectStack = remember { mutableStateSetOf<TechnicalStack>() }
    val tags = remember { mutableListOf<String>() }

    var projectStackSearch by remember { mutableStateOf("") }
    val allStacks = TechnicalStack.values().toList()
    var expandedStackSearch by remember { mutableStateOf(false) }

    val filteredTechStacks = remember(projectStackSearch) {
        if (projectStackSearch.isEmpty()) {
            allStacks
        } else {
            allStacks.filter {
                it.name.contains(projectStackSearch, ignoreCase = true)
            }
        }
    }

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = newProjectTitle, label = { Text("Project title") },
                    onValueChange = { newProjectTitle = it })

                TextField(
                    value = newProjectDescription, label = { Text("Project description") },
                    onValueChange = { newProjectDescription = it })

                Button(onClick = { expandedDropdown = true }) {
                    Text("Choose: $newProjectPayment")
                }

                Box() {
                    DropdownMenu(expanded = expandedDropdown, onDismissRequest = { expandedDropdown = false }) {
                        PaymentType.entries.map {
                            DropdownMenuItem(text = { Text(it.toString()) }, onClick = {
                                newProjectPayment = it
                                expandedDropdown = false
                            })
                        }
                    }
                }

                SearchBar(
                    inputField = {
                        TextField(value = projectStackSearch, onValueChange = {
                            projectStackSearch = it;
                            if (it.isNotEmpty() && !expandedStackSearch) {
                                expandedStackSearch = true
                            }
                        })
                    },
                    expanded = expandedStackSearch,
                    onExpandedChange = { expandedStackSearch = it },
                ) {

                    LazyColumn {
                        if (filteredTechStacks.isEmpty() && projectStackSearch.isNotEmpty()) {
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            "No results found for \"$projectStackSearch\"",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                )
                            }
                        } else {
                            items(filteredTechStacks) { result ->
                                ListItem(
                                    headlineContent = { Text(result.toString()) },
                                    leadingContent = { Text(" - ") },
                                    modifier = Modifier.clickable {
                                        newProjectStack.add(result)
                                        expandedStackSearch = false
                                    }
                                )
                            }
                        }
                    }
                }

                FlowRow(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    newProjectStack.map {
                        Text(
                            text = it.toString(),
                            color = Color.White,
                            modifier = Modifier.clip(
                                RoundedCornerShape(percent = 50)
                            ).background(Color.Blue).padding(5.dp).clickable {
                                newProjectStack.remove(it)
                            },
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                }

                Button(onClick = {
                    val newProjectEntry = ProjectEntity(
                        name = newProjectTitle,
                        description = newProjectDescription,
                        tags = arrayOf("tag1", "tag2"),
                        paymentType = newProjectPayment ?: PaymentType.GRATITUDE,
                        technicalStack = newProjectStack.toList().toTypedArray()
                    )
                    projects?.add(newProjectEntry)
                    scope.launch {

                        try {
                            val success = createProject(client, newProjectTitle, newProjectDescription, newProjectPayment, newProjectStack.toList(), currentUserId)
                            if (success) {
                                println("Success")
                            }
                        } catch (e: Exception) {
                            println("Creation failed")
                            e.printStackTrace()
                        }

                    }
                    navController.navigate(PostList)
                }) {
                    Text("Save post")
                }
            }
    }