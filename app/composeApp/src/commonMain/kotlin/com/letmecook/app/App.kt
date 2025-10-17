package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.letmecook.domain.project.PaymentType
import com.letmecook.domain.project.ProjectDetailedView
import com.letmecook.domain.project.ProjectEntity
import com.letmecook.domain.project.ProjectListView
import com.letmecook.domain.project.TechnicalStack
import com.letmecook.domain.user.UserView
import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object PostList

@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Button(onClick = {
                        navController.navigate(PostList)
                        }) {
                            Text("Home")
                        }
                        Button(onClick = { navController.navigate(Profile) }) {
                            Text("Profile")
                        }
                    },
                )
            }
        ) { innerPadding ->

            NavHost(navController = navController, startDestination = PostList, modifier = Modifier
               .safeContentPadding()
               .padding(innerPadding)
                .fillMaxSize()
            ) {
                composable<Profile> { UserView() }
                composable<PostList> { ProjectListView() }
            }

        }

    }
}