package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.letmecook.domain.project.MyProjectsView
import com.letmecook.domain.project.ProjectCreateView

import com.letmecook.domain.project.ProjectListView
import com.letmecook.domain.user.UserView
import kotlinx.serialization.Serializable


@Serializable
object Profile

@Serializable
object PostList

@Serializable
object MyApplications

@Serializable
object CreatePost

@Serializable
object Welcome
val userDb = HashMap<String, String>();
var currentUser: Pair<String, String>? = null;

var currentUserId = 1;

//var userAuthorized: MutableState<Boolean>? = null;

object MySharedModule {
    private val _jwtToken = mutableStateOf<String?>(null)
    val jwtToken: State<String?> get() = _jwtToken

    fun setToken(token: String) {
        _jwtToken.value = token
    }

    fun getToken(): String? = _jwtToken.value
}

@Composable
fun App() {

    val navController = rememberNavController()
    val userAuthorized by remember {
        derivedStateOf { !MySharedModule.getToken().isNullOrBlank() }
    }

    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            MinimalGlowBackground(
                modifier = Modifier.matchParentSize()
            )
            Scaffold(
                modifier = Modifier.background(Color.Transparent),
                bottomBar = {
                        if (userAuthorized) {
                            BottomAppBar(
                                containerColor = Color.Transparent,
                                actions = {
                                    Button(onClick = {
                                        navController.navigate(PostList)
                                    }) {
                                        Text("Home")
                                    }
                                    Button(onClick = { navController.navigate(Profile) }) {
                                        Text("Profile")
                                    }
                                    Button(
                                        onClick = { navController.navigate(CreatePost) },
                                    ) {
                                        Text("+")
                                    }
                                    Button(onClick = { navController.navigate(MyApplications) }) {
                                        Text("My Applications")
                                    }
                                },
                            )
                    }
                }
            ) { innerPadding ->

                Box(Modifier.fillMaxSize()) {
                    MinimalGlowBackground(
                        modifier = Modifier.matchParentSize()
                    )
                    NavHost(
                        navController = navController, startDestination = Welcome, modifier = Modifier
                        .safeContentPadding()
                        .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        composable<Profile> { UserView() }
                        composable<Welcome> {
                            LaunchedEffect(userAuthorized) {
                                if (userAuthorized) {
                                    navController.navigate(PostList) {
                                        popUpTo(Welcome) { inclusive = true }
                                    }
                                }
                            }

                            WelcomeScreen(navController)
                        }
                        composable<PostList> { ProjectListView() }
                        composable<MyApplications> { MyProjectsView() }
                        composable<CreatePost> { ProjectCreateView(navController) }
                    }

                }
            }

        }

    }
}