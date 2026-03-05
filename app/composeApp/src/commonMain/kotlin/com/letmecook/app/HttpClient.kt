package com.letmecook.app

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val API_URL = "http://192.168.1.11:8080"
val client = HttpClient(){

   install(Resources)
   install(Auth) {
        bearer {
            loadTokens { BearerTokens(MySharedModule.getToken() ?: "", "") }
        }
    }

   install(ContentNegotiation) {
      json(Json {
          ignoreUnknownKeys = true
      })
   }
   defaultRequest {
        url(API_URL)
    }
}