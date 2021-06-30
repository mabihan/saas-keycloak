package com.example.demo.gateway.database.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductDB(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        val companyName: String,
        val branchOfficeName: String,
        val productName: String,
        val unitType: String,
        val active: Boolean,
        val createdDate: LocalDateTime,
        val updatedDate: LocalDateTime? = null
)