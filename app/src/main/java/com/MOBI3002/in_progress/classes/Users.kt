package com.MOBI3002.in_progress.classes

// data class that essentially holds a "user" from the database and holds everything but user password
data class Users (
    val userId: Int,
    val name: String,
    val email: String
)
