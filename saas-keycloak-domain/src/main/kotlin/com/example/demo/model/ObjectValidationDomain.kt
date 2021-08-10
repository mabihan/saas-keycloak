package com.example.demo.model

data class ObjectValidationDomain(
        val valid: Boolean,
        val message: String? = "",
        val error: String? = ""
)
