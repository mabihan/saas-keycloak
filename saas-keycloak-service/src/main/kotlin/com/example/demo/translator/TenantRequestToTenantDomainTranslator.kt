package com.example.demo.translator

import com.example.demo.model.TenantDomain
import com.example.demo.model.TenantRequest
import java.time.LocalDateTime

class TenantRequestToTenantDomainTranslator {

    fun translate(tenantRequest: TenantRequest): TenantDomain {
        return TenantDomain(namespace = tenantRequest.namespace, contact = tenantRequest.email, createdDate = LocalDateTime.now())
    }

}
