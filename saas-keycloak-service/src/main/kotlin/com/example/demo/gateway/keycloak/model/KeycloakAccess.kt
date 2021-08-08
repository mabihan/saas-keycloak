package com.example.demo.gateway.keycloak.model

data class KeycloakAccess(
    val manageGroupMembership: Boolean,
    val view: Boolean,
    val mapRoles: Boolean,
    val impersonate: Boolean,
    val manage: Boolean
)
