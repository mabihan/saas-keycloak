package com.example.demo.translator

import com.example.demo.exception.tenant.TenantCreationRequestInvalidException
import com.example.demo.model.TenantCreateDomain
import com.example.demo.model.TenantRequest
import com.example.demo.sanitizer.TenantNamespaceSanitizer
import java.time.DateTimeException
import java.time.ZoneOffset

class TenantRequestToTenantDomainTranslator {

    fun translate(tenantRequest: TenantRequest): TenantCreateDomain {

        if (!TenantNamespaceSanitizer().validate(tenantNamespace = tenantRequest.namespace)) {
            throw TenantCreationRequestInvalidException("Tenant namespace invalid")
        }

        try {
            return TenantCreateDomain(
                namespace = tenantRequest.namespace,
                sanitizedNamespace = TenantNamespaceSanitizer().sanitize(tenantRequest.namespace),
                timeZone = ZoneOffset.of(tenantRequest.timeZone)
            )
        } catch (ex: DateTimeException) {
            throw TenantCreationRequestInvalidException("Tenant time zone invalid")
        }
    }

}
