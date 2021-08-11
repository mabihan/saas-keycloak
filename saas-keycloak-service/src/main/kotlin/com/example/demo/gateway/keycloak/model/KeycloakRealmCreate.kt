package com.example.demo.gateway.keycloak.model

import kotlin.String

// https://github.com/IBM/cloud-native-starter/blob/master/security/IKS/quarkus-realm.json

data class KeycloakRealmCreate(
    val id: String,
    val realm: String,
    val displayName: String,
    val enabled: Boolean,
    val sslRequired: String,
    val registrationAllowed: Boolean,
    val loginWithEmailAllowed: Boolean,
    val duplicateEmailsAllowed: Boolean,
    val resetPasswordAllowed: Boolean,
    val editUsernameAllowed: Boolean,
    val bruteForceProtected: Boolean,
    val smtpServer: KeycloakSmtpServer
)
