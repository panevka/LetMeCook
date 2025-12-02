package com.letmecook.domain.project

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.delete
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
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Resource("/api/project")
class Projects {
    @Resource("/all")
    class All(val parent: Projects = Projects(), val authorId: String?)

    @Resource("/join")
    class Join(val parent: Projects = Projects())

    @Resource("/{projectId}")
    class Delete(val parent: Projects = Projects(), val projectId: Int)

    @Resource("")
    class Create(val parent: Projects = Projects())

    @Resource("")
    class Search(val parent: Projects = Projects(), val search: String)
}

@Serializable
data class ProjectDto(
    val id: Int,
    val title: String,
    val description: String,
    val payment_type: PaymentType,
    val tech_stack: List<TechnicalStack>,
    val created_at: String,
    val author_username: String,
    val author_id: Long,
)

@Serializable
data class JoinProjectRequest(
    val project_id: Int,
    val user_id: Int
)

@Serializable
data class CreateProjectRequest(
    val user_id: Int,
    val title: String,
    val description: String,
    val payment_type: PaymentType,
    val tech_stack: List<TechnicalStack>
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

suspend fun createProject(
    client: HttpClient,
    title: String,
    description: String,
    paymentType: PaymentType,
    techStack: List<TechnicalStack>,
    userId: Int,
): Boolean {
    val response: HttpResponse = client.post(Projects.Create()) {
        contentType(ContentType.Application.Json)
        setBody(CreateProjectRequest(userId, title, description ,paymentType, techStack))
    }
    return response.status == HttpStatusCode.OK
}
suspend fun getAllProjects(
    client: HttpClient,
    authorId: String? = null,
): HttpResponse {
val response: HttpResponse = client.get(Projects.All(authorId = authorId)) {
        contentType(ContentType.Application.Json)
    }
    return response
}

suspend fun searchProjects(
    client: HttpClient,
    search: String
): HttpResponse {
    val response: HttpResponse = client.get(Projects.Search(search = search)) {
        contentType(ContentType.Application.Json)
    }
    return response
}


suspend fun deleteProject(
    client: HttpClient,
    projectId: Int,
): Boolean {
    val response: HttpResponse = client.delete(Projects.Delete(projectId = projectId)) {
        contentType(ContentType.Application.Json)
    }
    return response.status == HttpStatusCode.OK
}
