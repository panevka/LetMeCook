package com.letmecook.domain.project

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("/project")
class Projects {
    @Resource("/all")
    class All(val parent: Projects = Projects())

    @Resource("/join")
    class Join(val parent: Projects = Projects())
}

@Serializable
data class ProjectDto(
    val id: Int,
    val title: String
)

@Serializable
data class JoinProjectRequest(
    val project_id: Int,
    val user_id: Int
)


suspend fun joinProject(
    client: HttpClient,
    projectId: Int,
    userId: Int
): Boolean {
    val response: HttpResponse = client.post(Projects.Join()) {
        contentType(ContentType.Application.Json)
        setBody(JoinProjectRequest(projectId, userId))
    }
    return response.status == HttpStatusCode.OK
}