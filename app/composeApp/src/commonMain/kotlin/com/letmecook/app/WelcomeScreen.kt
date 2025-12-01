package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun WelcomeScreen (navController: NavController) {

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text("Let Me Cook", color = AppColors.SecondaryFontColor, style = MaterialTheme.typography.displayMedium)
        Text("Connect. Build. Grow together.", color = AppColors.PrimaryFontColor)

        Column(modifier = Modifier.padding(horizontal = 50.dp)){
            ListItem("Real Projects", "Work on actual apps with real impact", "🚀")

            HorizontalDivider(thickness = 0.2.dp)

            ListItem("Find Your Team", "Connect with developers and idea owners", "👥")

            HorizontalDivider(thickness = 0.2.dp)

            ListItem("Build Portfolio", "Turn collaboration into career opportunities", "⚡")

            HorizontalDivider(thickness = 0.2.dp)

            Button(
                onClick = {
                    uriHandler.openUri(API_URL + "/api/authorize/discord")
                },
                content = { Text("Continue with Discord", color = AppColors.Black) },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Text("By continuing, you agree to our Terms of Service and Privacy Policy.", color = AppColors.Black)
        }


    }
}

@Composable
fun ListItem(headline: String, description: String, emote: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .size(48.dp)
                .background(AppColors.Secondary, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            val fontSize = with(LocalDensity.current) { (maxHeight * 0.7f).toSp() }

            Text(
                text = emote,
                fontSize = fontSize
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                headline,
                color = AppColors.SecondaryFontColor,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                description,
                color = AppColors.PrimaryFontColor
            )
        }
    }
}
