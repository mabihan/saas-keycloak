package com.example.demo.translator

import com.example.demo.exception.tenant.TenantNamespaceInvalidException
import com.example.demo.model.TenantCreateDomain
import com.example.demo.model.TenantRequest
import com.example.demo.sanitizer.TenantNamespaceSanitizer
import java.time.ZoneOffset

class TenantRequestToTenantDomainTranslator {

    fun translate(tenantRequest: TenantRequest): TenantCreateDomain {

        if (!TenantNamespaceSanitizer().validate(tenantNamespace = tenantRequest.namespace)) {
            throw TenantNamespaceInvalidException("Tenant namespace invalid")
        }

        // TODO: Surround with try/catch to handle timezone conversion from string errors.
        return TenantCreateDomain(
            namespace = tenantRequest.namespace,
            sanitizedNamespace = TenantNamespaceSanitizer().sanitize(tenantRequest.namespace),
            timeZone = ZoneOffset.of(tenantRequest.timeZone)
            )
    }

}
