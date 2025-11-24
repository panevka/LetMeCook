package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.letmecook.domain.account.LoginView
import com.letmecook.domain.account.SignUpView
import com.letmecook.domain.account.SignUpView
import com.letmecook.domain.account.SignUpView2
import com.letmecook.domain.project.ProjectCreateView
import com.letmecook.domain.project.ProjectDto
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.letmecook.domain.project.ProjectListView
import com.letmecook.domain.project.Projects
import com.letmecook.domain.user.UserView
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.logging.Logger
import io.ktor.util.rootCause
import kotlinx.serialization.Serializable


@Serializable
object Profile

@Serializable
object PostList

@Serializable
object Login

@Serializable
object SignUp

@Serializable
object CreatePost

val userDb = HashMap<String, String>();
var currentUser: Pair<String, String>? = null;

var currentUserId = 1;

var userAuthorized: MutableState<Boolean>? = null;

object MySharedModule {
    private var jwtToken: String? = null

    fun setToken(token: String) {
        jwtToken = token
    }

    fun getToken(): String? = jwtToken
}

@Composable
fun App() {

    val navController = rememberNavController()
    userAuthorized = remember{mutableStateOf(true)}

    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            MinimalGlowBackground(
                modifier = Modifier.matchParentSize()
            )
            Scaffold(
                modifier = Modifier.background(Color.Transparent),
                bottomBar = {
                        if (userAuthorized?.value == true) {
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
                        navController = navController, startDestination = SignUp, modifier = Modifier
                        .safeContentPadding()
                        .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        composable<Profile> { UserView() }
                        composable<PostList> { ProjectListView() }
                        composable<Login> { LoginView(navController) }
//                        composable<SignUp> { SignUpView(navController) }
                        composable<SignUp> { SignUpView2() }
                        composable<CreatePost> { ProjectCreateView(navController) }
                    }

                }
            }

        }

    }
}