package com.example.demo.model

data class UserValidationDomain(
        val valid: Boolean,
        val message: String? = "",
        val error: String? = ""
)
