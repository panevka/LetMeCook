package com.letmecook.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
