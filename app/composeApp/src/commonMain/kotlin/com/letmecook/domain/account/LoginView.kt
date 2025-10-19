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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.letmecook.app.SignUp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.sign

@Preview
@Composable
internal fun LoginView(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(value = "aha", onValueChange = {}, modifier = Modifier.width(300.dp), label = { Text("E-mail") })
        TextField(value = "aha", onValueChange = {}, modifier = Modifier.width(300.dp), label = { Text("Password") })

        Button(onClick = {}, modifier = Modifier.width(300.dp)) {
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