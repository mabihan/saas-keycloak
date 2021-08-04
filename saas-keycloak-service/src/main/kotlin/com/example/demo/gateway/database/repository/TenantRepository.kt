package com.example.demo.gateway.database.repository

import com.example.demo.gateway.database.model.TenantDB
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable

@Repository
interface TenantRepository : JpaRepository<TenantDB, Long> {

    fun existsByNamespace(name: String): Boolean

    fun findByNamespace(name: String): TenantDB
}
