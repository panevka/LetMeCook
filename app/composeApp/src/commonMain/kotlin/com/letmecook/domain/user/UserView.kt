package com.letmecook.domain.user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.composeapp.generated.resources.Res
import app.composeapp.generated.resources.allDrawableResources
import app.composeapp.generated.resources.avatar
import app.composeapp.generated.resources.compose_multiplatform
import com.letmecook.app.AppColors
import com.letmecook.app.client
import com.letmecook.app.currentUser
import com.letmecook.app.currentUserId
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun UserView () {

        var showPopup by remember { mutableStateOf(false) }

    var user by remember { mutableStateOf<UserDto?>(null)}

        LaunchedEffect(true){
            try {
                val response: UserDto = getUser(client,currentUserId.toLong()).body()
                user = response
            } catch (e: Exception){
                println("Error fetching user")
                println(e.printStackTrace())
            }
        }

//        var mockUser by remember { mutableStateOf(UserDto(
//            username = "JanKow",
//            first_name = "Jan",
//            last_name = "Kowalski",
//            avatar_url = "https://img.freepik.com/free-psd/yellow-gift-with-golden-ribbon-icon-sign-symbol-3d-background-illustration_56104-2422.jpg",
//            id = 1
//        ))}

    val mockUser = user;
    if(mockUser == null){
       Text(
           "Could not fetch"
       )
        return
    }



        val fullName = "${mockUser.first_name} ${mockUser.last_name}"

        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            KamelImage(
                resource = asyncPainterResource(data = mockUser.avatar_url),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(128.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

//            Image(
//                painter = painterResource(Res.drawable.avatar),
//                contentDescription = "aha",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .requiredSize(128.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )
            Text(mockUser.username, style = MaterialTheme.typography.displayMedium, color = Color.White)

            Text("About", style = MaterialTheme.typography.headlineLarge, color = Color.White, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())


            Box(modifier = Modifier.background(AppColors.Primary, shape = RoundedCornerShape(20.dp)).clip(shape = RoundedCornerShape(20.dp)).padding(30.dp)

            ){
                Text(mockUser.bio.toString(), style = MaterialTheme.typography.titleLarge, color = AppColors.PrimaryFontColor)
            }

            Text("Skills", style = MaterialTheme.typography.headlineLarge, color = Color.White, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())

            Button(
                onClick = { showPopup = true },
            ){
               Text("Edit profile")
            }

        }


//    if (showPopup) {
//        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
//            Column(verticalArrangement = Arrangement.Top){
//            TextField(
//                value = mockUser.username,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(username = newValue)
//                } ,
//                label = {Text("nickname")}
//            )
//            TextField(
//                value = mockUser.first_name,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(firstName = newValue)
//                } ,
//                label = {Text("firstName")}
//            )
//            TextField(
//                value = mockUser.lastName,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(lastName = newValue)
//                } ,
//                label = {Text("lastName")}
//            )
//            TextField(
//                value = mockUser.bio,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(bio = newValue)
//                } ,
//                label = {Text("bio")}
//            )
//            TextField(
//                value = mockUser.websiteUrl,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(websiteUrl = newValue)
//                } ,
//                label = {Text("websiteUrl")}
//            )
//            TextField(
//                value = mockUser.avatarUrl,
//                onValueChange = { newValue: String ->
//                    mockUser = mockUser.copy(avatarUrl = newValue)
//                } ,
//                label = {Text("avatarUrl")}
//            )
//
//                TextField(
//                    value = mockUser.githubUrl,
//                    onValueChange = { newValue: String ->
//                        mockUser = mockUser.copy(githubUrl = newValue)
//                    } ,
//                    label = {Text("Github Account")}
//                )
//                Button(onClick = { showPopup = false}){
//                    Text("Save")
//                }
//        }
//        }
//    }


}
