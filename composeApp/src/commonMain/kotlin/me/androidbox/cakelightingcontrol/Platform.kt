package me.androidbox.cakelightingcontrol

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform