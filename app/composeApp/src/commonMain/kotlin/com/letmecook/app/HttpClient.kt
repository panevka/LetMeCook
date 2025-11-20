package com.letmecook.app

import androidx.compose.ui.platform.LocalUriHandler
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources

val API_URL = "http://192.168.1.11:8080/api/"
val client = HttpClient(){
   install(Resources)
    defaultRequest {
        url(API_URL)
    }
}