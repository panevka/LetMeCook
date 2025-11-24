package com.letmecook.domain.account

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import com.letmecook.app.API_URL
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun SignUpView2 () {
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()

    Button(onClick={
       uriHandler.openUri(API_URL + "/api/authorize/discord")
    })   {
        Text("Sign Up with Discord")
    }
}