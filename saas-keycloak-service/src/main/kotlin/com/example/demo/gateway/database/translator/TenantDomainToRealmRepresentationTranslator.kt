package com.example.demo.gateway.database.translator

import com.example.demo.model.TenantCreateDomain
import org.keycloak.representations.idm.RealmRepresentation

class TenantDomainToRealmRepresentationTranslator {

    fun translate(tenantCreateDomain: TenantCreateDomain): RealmRepresentation {
        val realmRepresentation = RealmRepresentation()

        realmRepresentation.displayName = tenantCreateDomain.namespace
        realmRepresentation.displayNameHtml = "<div class=\"kc-logo-text\"><span>${tenantCreateDomain.namespace}</span></div>"
        realmRepresentation.isEnabled = true

        return realmRepresentation
    }
}
