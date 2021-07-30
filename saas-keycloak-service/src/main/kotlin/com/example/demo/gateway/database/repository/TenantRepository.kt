package com.example.demo.gateway.database.repository

import com.example.demo.gateway.database.model.TenantDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TenantRepository : JpaRepository<TenantDB, Long> {
}