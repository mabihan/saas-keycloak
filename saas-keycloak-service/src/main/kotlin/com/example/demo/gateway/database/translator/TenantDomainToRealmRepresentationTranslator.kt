package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.model.TenantDomain
import org.keycloak.representations.idm.RealmRepresentation

class TenantDomainToRealmRepresentationTranslator {

    fun translate(tenantDomain: TenantDomain): RealmRepresentation {
        val realmRepresentation = RealmRepresentation()

        realmRepresentation.displayName = tenantDomain.namespace
        realmRepresentation.displayNameHtml = "<div class=\"kc-logo-text\"><span>${tenantDomain.namespace}</span></div>"
        realmRepresentation.isEnabled = true

        return realmRepresentation
    }
}
