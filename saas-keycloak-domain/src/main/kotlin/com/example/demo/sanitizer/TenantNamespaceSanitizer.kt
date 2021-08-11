package com.example.demo.sanitizer

import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import java.util.regex.Matcher
import java.util.regex.Pattern

class TenantNamespaceSanitizer {

    fun sanitize(tenantNamespace: String): String {
        val re = Regex("[^A-Za-z0-9 ]")
        return re.replace(tenantNamespace, "").toLowerCaseAsciiOnly().replace(" ","")
    }

    fun validate(tenantNamespace: String): Boolean {
        return this.regexTenantNamespace(tenantNamespace)
    }

    private fun regexTenantNamespace(tenantNamespace: String): Boolean {
        val tenantNamespacePattern: Pattern = Pattern.compile("^[a-z\\d\\-_\\s]+\$")
        val tenantNamespaceMatcher: Matcher = tenantNamespacePattern.matcher(tenantNamespace)
        return tenantNamespaceMatcher.matches()
    }
}
