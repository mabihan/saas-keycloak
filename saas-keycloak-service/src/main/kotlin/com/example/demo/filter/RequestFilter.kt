package com.example.demo.filter

import com.example.demo.config.multitenant.TenantContextHolder
import com.example.demo.exception.BadRequestException
import org.keycloak.KeycloakPrincipal
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestFilter : OncePerRequestFilter() {

    @Value("\${spring.liquibase.default-schema}")
    val defaultSchema = ""

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val schemaName = getSchemaFromAuth()

            if (StringUtils.hasText(schemaName)) {
                TenantContextHolder.setCurrentSchema(schemaName)
            } else {
                TenantContextHolder.setDefaultSchema(defaultSchema)
            }
        } catch (ex: BadRequestException) {
            throw ex
        } catch (ex: RuntimeException) {
            TenantContextHolder.setDefaultSchema(defaultSchema)
        }

        filterChain.doFilter(request, response)
    }

    private fun getSchemaFromAuth(): String? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            val principal = authentication.principal

            if (principal is KeycloakPrincipal<*>) {
                val token = (KeycloakPrincipal::class.java.cast(principal) as KeycloakPrincipal<*>).keycloakSecurityContext.token
                val customClaims = token.otherClaims
                val schemaName = customClaims["schema_name"] ?: throw BadRequestException("The user must have schema name.")
                return schemaName.toString()
            }
        }
        return null
    }

}