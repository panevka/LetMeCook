package com.letmecook.domain.user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.composeapp.generated.resources.Res
import app.composeapp.generated.resources.allDrawableResources
import app.composeapp.generated.resources.avatar
import app.composeapp.generated.resources.compose_multiplatform
import com.letmecook.app.currentUser
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun UserView () {

        var showPopup by remember { mutableStateOf(false) }
        var mockUser by remember { mutableStateOf(User(
            username = "JanKow",
            firstName = "Jan",
            lastName = "Kowalski",
            avatarUrl = "https://img.freepik.com/free-psd/yellow-gift-with-golden-ribbon-icon-sign-symbol-3d-background-illustration_56104-2422.jpg" ,
            bio = "sample bioooo a123123dfafjkl",
            websiteUrl ="https://docs.github.com/en/pages" ,
            githubUrl = "https://github.com/torvalds"
        ))}

        val fullName = "${mockUser.firstName} ${mockUser.lastName}"

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("text user profile view")
            Text("currently logged in user: ${currentUser?.first} ${currentUser?.second}")

            Image(
                painter = painterResource(Res.drawable.avatar),
                contentDescription = "aha",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(mockUser.username, style = MaterialTheme.typography.displayLarge)
            Text(text=fullName, style = MaterialTheme.typography.titleMedium)
            Text(text=mockUser.bio ?: "", style = MaterialTheme.typography.bodySmall)
            Text(text= mockUser.websiteUrl.let { "Website: $it" }, style = MaterialTheme.typography.bodySmall)

            Text(text= mockUser.githubUrl.let { "Github: $it" }, style = MaterialTheme.typography.bodySmall)
            Button(
                onClick = { showPopup = true },
            ){
               Text("Edit profile")
            }

        }


    if (showPopup) {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
            Column(verticalArrangement = Arrangement.Top){
            TextField(
                value = mockUser.username,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(username = newValue)
                } ,
                label = {Text("nickname")}
            )
            TextField(
                value = mockUser.firstName,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(firstName = newValue)
                } ,
                label = {Text("firstName")}
            )
            TextField(
                value = mockUser.lastName,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(lastName = newValue)
                } ,
                label = {Text("lastName")}
            )
            TextField(
                value = mockUser.bio,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(bio = newValue)
                } ,
                label = {Text("bio")}
            )
            TextField(
                value = mockUser.websiteUrl,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(websiteUrl = newValue)
                } ,
                label = {Text("websiteUrl")}
            )
            TextField(
                value = mockUser.avatarUrl,
                onValueChange = { newValue: String ->
                    mockUser = mockUser.copy(avatarUrl = newValue)
                } ,
                label = {Text("avatarUrl")}
            )

                TextField(
                    value = mockUser.githubUrl,
                    onValueChange = { newValue: String ->
                        mockUser = mockUser.copy(githubUrl = newValue)
                    } ,
                    label = {Text("Github Account")}
                )
                Button(onClick = { showPopup = false}){
                    Text("Save")
                }
        }
        }
    }


}
