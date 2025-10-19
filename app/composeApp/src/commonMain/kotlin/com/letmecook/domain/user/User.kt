package com.letmecook.domain.user

internal data class User(
    var username: String,
    var firstName: String,
    var lastName: String,
    var avatarUrl: String,
    var bio: String,
    var websiteUrl: String,
    var githubUrl: String
//    var externalAccounts: Map<ExternalAccount.Platform, ExternalAccount>?
)

//internal data class ExternalAccount(val platform: Platform, val url: String ) {
//    enum class Platform {
//        GITHUB, LINKEDIN
//    }
//};
