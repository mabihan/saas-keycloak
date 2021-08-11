package com.example.demo.gateway.tenant

import java.util.*

interface DoesTenantUuidExistGateway {

    fun execute(uuid: UUID): Boolean

}
