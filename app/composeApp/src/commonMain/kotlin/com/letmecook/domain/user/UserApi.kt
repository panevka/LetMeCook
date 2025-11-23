package com.letmecook.domain.user

import com.letmecook.domain.project.PaymentType
import com.letmecook.domain.project.TechnicalStack
import kotlinx.serialization.Serializable

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

data class GetUserDto {
    private val id: Long? = null
    private val username: String? = null
    private val firstName: String? = null
    private val lastName: String? = null
    private val avatarUrl: String? = null
}

