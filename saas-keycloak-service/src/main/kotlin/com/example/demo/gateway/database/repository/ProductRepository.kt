package com.example.demo.gateway.database.repository

import com.example.demo.gateway.database.model.ProductDB
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductDB, Long> {

    fun existsByCompanyNameAndBranchOfficeNameAndProductNameAndUnitTypeAndActive(companyName: String, branchOfficeName: String,
                                                                                 productName: String, unitType: String, active: Boolean): Boolean

    fun findByCompanyNameAndActive(pageable: Pageable, companyName: String, active: Boolean): Page<ProductDB>

}