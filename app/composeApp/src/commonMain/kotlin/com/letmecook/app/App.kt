package com.letmecook.app

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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.letmecook.domain.account.LoginView
import com.letmecook.domain.account.SignUpView
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.letmecook.domain.project.ProjectListView
import com.letmecook.domain.user.UserView
import kotlinx.serialization.Serializable


@Serializable
object Profile

@Serializable
object PostList

@Serializable
object Login

@Serializable
object SignUp

val userDb = HashMap<String, String>();
var currentUser: Pair<String, String>? = null;

var userAuthorized: MutableState<Boolean>? = null;
@Composable
@Preview
fun App() {

    val navController = rememberNavController()
    userAuthorized = remember{mutableStateOf(true)}

    MaterialTheme {
        Scaffold(
            bottomBar = {
                if(userAuthorized?.value == true) {
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
                            Button(
                                onClick = {},
                            ) {
                                Text("+")
                            }
                        },
                    )
                }
            }
        ) { innerPadding ->

            NavHost(navController = navController, startDestination = Login, modifier = Modifier
               .safeContentPadding()
               .padding(innerPadding)
                .fillMaxSize()
            ) {
                composable<Profile> { UserView() }
                composable<PostList> { ProjectListView() }
                composable<Login> { LoginView(navController) }
                composable<SignUp> { SignUpView(navController) }
            }

        }

    }
}