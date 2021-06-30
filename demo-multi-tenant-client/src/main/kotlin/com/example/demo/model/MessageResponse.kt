package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonInclude

data class MessageResponse(
        val httpCode: Int,
        val message: String,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        val errors: List<String>? = null
)