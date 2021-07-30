package com.example.demo.translator

import com.example.demo.model.TenantDomain
import com.example.demo.model.TenantResponse

class TenantDomainToTenantResponseTranslator {

    fun translate(tenantDomain: TenantDomain): TenantResponse {
        return TenantResponse(namespace = tenantDomain.namespace, contact = "not implemented", createdDate = tenantDomain.createdDate)
    }

}
