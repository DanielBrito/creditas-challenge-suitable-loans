package com.creditas.loan.inbound.controllers

import com.creditas.loan.applications.SuitableLoanApplication
import com.creditas.loan.inbound.controllers.resources.CustomerRequest
import com.creditas.loan.inbound.controllers.resources.SuitableLoansResponse
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoanController(
    val suitableLoanApplication: SuitableLoanApplication
) {

    @PostMapping("/suitable-loans")
    fun getSuitableLoans(
        @RequestBody customerRequest: CustomerRequest
    ): ResponseEntity<SuitableLoansResponse> {
        val suitableLoans = suitableLoanApplication.process(customerRequest.toCustomer())
        val suitableLoansResponse = SuitableLoansResponse.from(customerRequest.getName(), suitableLoans)

        return ResponseEntity(suitableLoansResponse, OK)
    }
}
