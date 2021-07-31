package com.example.demo.model

import java.time.LocalDateTime

data class TenantDomain(
        val id: Long? = null,
        val namespace: String,
        val contact: String,
        val createdDate: LocalDateTime,
        val updatedDate: LocalDateTime? = null
)
