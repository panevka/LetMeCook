package com.letmecook.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
    override fun onNewIntent(intent: Intent) {
        println("on new intent")
        super.onNewIntent(intent)
        intent.data?.let { uri ->
            if (uri.scheme == "letmecook" && uri.host == "auth") {
                val token = uri.getQueryParameter("token")
                token?.let {
                    println("token: $token")
                    MySharedModule.setToken(it)
                }
            }
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}