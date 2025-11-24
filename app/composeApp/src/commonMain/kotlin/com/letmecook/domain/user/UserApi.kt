package com.letmecook.domain.user

import com.letmecook.domain.project.JoinProjectRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRetryEventData
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("/api/user")
class Users {
    @Resource("/{id}")
    class GetById(val parent: Users = Users(), val id: Long)

    @Resource("/{id}")
    class Update(val parent: Users = Users(), val id: Long)
}

@Resource("/api/authorize")
class Authorize {
    @Resource("/discord")
    class discord(val parent: Users = Users())
}


suspend fun getUser(client: HttpClient, userId: Long): HttpResponse {

    val response: HttpResponse = client.get(Users.GetById(id=userId)) {
        contentType(ContentType.Application.Json)
    }
    return response;
}

suspend fun updateUser(client: HttpClient, userId: Long, data: PatchUserDto): HttpResponse {
    val response: HttpResponse = client.patch(Users.Update(id=userId)) {
        setBody(data)
        contentType(ContentType.Application.Json)
    }
    return response;
}
@Serializable
data class UserDto (
    val id: Long,
    val username: String,
    val first_name: String?,
    val last_name: String?,
    val bio: String?,
    val avatar_url: String
)

@Serializable
data class PatchUserDto (
    val first_name: String?,
    val last_name: String?,
    val bio: String?,
)
