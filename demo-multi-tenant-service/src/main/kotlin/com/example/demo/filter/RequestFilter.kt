package com.example.demo.filter

import org.springframework.web.filter.OncePerRequestFilter
import kotlin.Throws
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.FilterChain
import com.example.demo.config.multitenant.TenantContextHolder
import com.example.demo.exception.BadRequestException
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.util.StringUtils

class RequestFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val schemaName = getSchemaFromAuth(request)

            if (StringUtils.hasText(schemaName)) {
                TenantContextHolder.setCurrentSchema(schemaName)
            } else {
                TenantContextHolder.setDefaultSchema()
            }
        } catch (ex: BadRequestException) {
            throw ex
        } catch (ex: RuntimeException) {
            TenantContextHolder.setDefaultSchema()
        }

        filterChain.doFilter(request, response)
    }

    private fun getSchemaFromAuth(request: HttpServletRequest): String? {
        return if (request.userPrincipal != null) {
            val keycloakAuthentication = request.userPrincipal as KeycloakAuthenticationToken
            val token = keycloakAuthentication.account.keycloakSecurityContext.token
            val customClaims = token.otherClaims
            val schemaName = customClaims["schema_name"] ?: throw BadRequestException("The user must have schema name.")
            return schemaName.toString()
        } else null
    }

}