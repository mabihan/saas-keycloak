package com.example.demo.sanitizer

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailSanitizer {
    fun sanitize(email: String): String {
        return email
    }

    fun validate(email: String): Boolean {
        return this.sanitize(email) == email && this.regexEmail(email)
    }

    private fun regexEmail(email: String): Boolean {
        val emailPattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        val emailMatcher: Matcher = emailPattern.matcher(email)
        return emailMatcher.matches()
    }
}
