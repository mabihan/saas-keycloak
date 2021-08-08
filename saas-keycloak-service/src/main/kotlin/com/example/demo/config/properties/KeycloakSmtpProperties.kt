package com.example.demo.config.properties

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class KeycloakSmtpProperties {
    var password: String = "password"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var replyToDisplayName: String = ""
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var starttls: Boolean = false

    var auth: Boolean = false

    var port: Int = 25

    var host: String = "localhost"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var replyTo: String = "noreply@example.com"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var from: String = "noreply@example.com"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var fromDisplayName: String = ""
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }

    var ssl: Boolean = false

    var user: String = "admin"
        set(value) {
            field = when {
                value.isNotEmpty() -> value
                else -> value
            }
        }
}
