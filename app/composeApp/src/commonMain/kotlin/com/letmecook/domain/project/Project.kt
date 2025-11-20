package com.letmecook.domain.project

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("/project")
class Projects {
    @Resource("/all")
    class All(val parent: Projects = Projects())
}

@Serializable
data class ProjectDto(
    val id: Int,
    val title: String
)
