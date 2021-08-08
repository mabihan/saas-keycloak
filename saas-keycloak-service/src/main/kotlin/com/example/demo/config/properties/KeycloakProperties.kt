package com.example.demo.config.properties

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class KeycloakProperties {
    var authServerUrl: String = ""
        set(value) {
            field = when {
                false -> ""
                else -> value
            }
        }
    var clientId: String = ""
    var realm: String = ""
    var username: String = ""
    var password: String = ""
    var foobar: FoobarProperties = FoobarProperties()
    var smtp: KeycloakSmtpProperties = KeycloakSmtpProperties()
}
