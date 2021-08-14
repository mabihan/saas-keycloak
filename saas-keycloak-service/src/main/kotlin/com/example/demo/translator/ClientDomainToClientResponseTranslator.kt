package com.example.demo.translator

import com.example.demo.model.ClientDomain
import com.example.demo.model.ClientResponse
import com.example.demo.model.UserDomain
import com.example.demo.model.UserResponse
import java.time.Instant
import java.time.ZoneId
import java.util.*


class ClientDomainToClientResponseTranslator {

    fun translate(clientDomain: ClientDomain): ClientResponse {

        return ClientResponse(
            id = clientDomain.id,
            clientId = clientDomain.clientId,
            name = clientDomain.name,
            rootUrl = clientDomain.rootUrl,
            baseUrl = clientDomain.baseUrl
        )
    }

}
