package com.example.demo.gateway.database.model

import javax.persistence.*

@Entity
@Table(name = "tenant")
class TenantDB(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val namespace: String,

        @Column(name = "db_schema")
        val schema: String,
)
