package com.example.demo.gateway.database.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductDB(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "company_name")
        val companyName: String,

        @Column(name = "branch_office_name")
        val branchOfficeName: String,

        @Column(name = "product_name")
        val productName: String,

        @Column(name = "unit_type")
        val unitType: String,

        val active: Boolean,

        @Column(name = "created_date")
        val createdDate: LocalDateTime,

        @Column(name = "updated_date")
        val updatedDate: LocalDateTime? = null
)