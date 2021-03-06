package com.example.demo.gateway.keycloak.model

data class KeycloakRealmClientCreate(
    val name: String,
    val adminUrl: String,
    val rootUrl: String,
    val description: String,
    val surrogateAuthRequired: Boolean,
    val enabled: Boolean,
    val alwaysDisplayInConsole: Boolean,
    val clientAuthenticatorType: String,
    val redirectUris: List<String>,
    val webOrigins: List<String>,
    val notBefore: Long,
    val bearerOnly: Boolean,
    val consentRequired: Boolean,
    val standardFlowEnabled: Boolean,
    val implicitFlowEnabled: Boolean,
    val directAccessGrantsEnabled: Boolean,
    val serviceAccountsEnabled: Boolean,
    val publicClient: Boolean,
    val frontchannelLogout: Boolean,
    val protocol: String,
    val fullScopeAllowed: Boolean,
    val nodeReRegistrationTimeout: Long,
    val defaultClientScopes: List<String>,
    val optionalClientScopes: List<String>,
    val access: KeycloakRealmClientAccessCreate
)
