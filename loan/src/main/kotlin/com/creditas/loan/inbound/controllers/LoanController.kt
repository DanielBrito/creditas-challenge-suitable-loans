package com.creditas.loan.inbound.controllers

import com.creditas.loan.applications.SuitableLoanApplication
import com.creditas.loan.inbound.controllers.resources.PersonInfoRequest
import com.creditas.loan.inbound.controllers.resources.SuitableLoansResponse
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoanController(
    val suitableLoanApplication: SuitableLoanApplication
) {

    @GetMapping("/suitable-loans")
    fun getSuitableLoans(
        @RequestBody personInfoRequest: PersonInfoRequest
    ): ResponseEntity<SuitableLoansResponse> {
        val suitableLoans = suitableLoanApplication.process(personInfoRequest.toPersonInfo())
        val suitableLoansResponse = SuitableLoansResponse.from(personInfoRequest.getName(), suitableLoans)

        return ResponseEntity(suitableLoansResponse, OK)
    }
}
