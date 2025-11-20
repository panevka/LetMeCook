package com.letmecook.app

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val API_URL = "http://192.168.1.11:8080/api/"
val client = HttpClient(){
   install(Resources)
   install(ContentNegotiation) {
      json(Json {
          ignoreUnknownKeys = true
      })
   }
   defaultRequest {
        url(API_URL)
    }
}