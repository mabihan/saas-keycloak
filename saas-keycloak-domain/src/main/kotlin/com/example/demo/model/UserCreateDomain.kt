package com.example.demo.model

data class UserCreateDomain(
        val username: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String
)
