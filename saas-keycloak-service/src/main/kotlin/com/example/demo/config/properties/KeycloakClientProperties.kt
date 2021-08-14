package com.example.demo.config.properties

import org.springframework.boot.context.properties.ConstructorBinding
import java.net.URI

@ConstructorBinding
class KeycloakClientProperties {
    var clientId: String = "saas-frontend-client"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var adminUrl: URI = URI("http://localhost/")

    var rootUrl: URI = URI("http://localhost/")

    var clientAuthenticatorType: String = "client-secret"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var redirectUris: List<URI> = listOf<URI>()
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }


    var protocol: String = "openid-connect"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }


}
