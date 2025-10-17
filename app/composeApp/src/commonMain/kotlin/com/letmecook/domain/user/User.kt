package com.letmecook.domain.user

internal data class User(
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val avatarUrl: String?,
    val bio: String?,
    val websiteUrl: String?,
    val externalAccounts: Map<ExternalAccount.Platform, ExternalAccount>
)

internal data class ExternalAccount(val platform: Platform, val url: String ) {
    enum class Platform {
        GITHUB, LINKEDIN
    }
};
