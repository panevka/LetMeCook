package com.letmecook.domain.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.letmecook.app.SignUp
import com.letmecook.app.currentUser
import com.letmecook.app.userDb
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.sign

@Preview
@Composable
internal fun LoginView(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var text by remember {mutableStateOf("Sign in")}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text)
        TextField(value = email , onValueChange = { email = it}, modifier = Modifier.width(300.dp), label = { Text("E-mail") })
        TextField(value = password, onValueChange = { password = it}, modifier = Modifier.width(300.dp), label = { Text("Password") })

        Button(onClick = {
            text = if(userDb[email] == password){
                currentUser = Pair(email, password)
                "Successful login"
            } else {
                "Wrong password"
            }

        }, modifier = Modifier.width(300.dp)) {
            Text("Log in")
        }

        Text("Don't have an account?", style = MaterialTheme.typography.bodySmall)
        TextButton(onClick = {navController.navigate(SignUp)}){
            Text(
                "Sign up",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}