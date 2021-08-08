package com.example.demo.gateway.database.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tenant")
class TenantDB(

        @Id
        @Column(name = "uuid")
        val uuid: UUID = UUID.randomUUID(),

        @Column(name = "namespace")
        val namespace: String,

        @Column(name = "schema_name")
        val schemaName: String,

        @Column(name = "keycloak_realm")
        val keycloakRealm: String,

        @Column(name = "time_zone")
        val timeZone: String,
)
