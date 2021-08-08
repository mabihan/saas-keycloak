package com.example.demo.model

data class UserDomain(
        val id: String,
        val createdTimestamp: Long,
        val username: String,
        val enabled: Boolean,
        val totp: Boolean,
        val emailVerified: Boolean,
        val firstName: String,
        val lastName: String,
        val email: String,
)
