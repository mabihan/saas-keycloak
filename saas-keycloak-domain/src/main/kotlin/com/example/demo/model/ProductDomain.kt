package com.example.demo.model

import java.time.LocalDateTime

data class ProductDomain(
        val id: Long? = null,
        val companyName: String,
        val branchOfficeName: String,
        val productName: String,
        val unitType: String,
        val active: Boolean,
        val createdDate: LocalDateTime,
        val updatedDate: LocalDateTime? = null
)