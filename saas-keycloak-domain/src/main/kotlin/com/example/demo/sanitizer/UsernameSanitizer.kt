package com.example.demo.sanitizer

class UsernameSanitizer {
    fun sanitize(username: String): String {
        return username
    }

    fun validate(username: String): Boolean {
        return this.sanitize(username) == username
    }
}
