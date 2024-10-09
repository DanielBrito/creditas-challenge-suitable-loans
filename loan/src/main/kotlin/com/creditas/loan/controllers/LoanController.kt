package com.creditas.loan.controllers

import com.creditas.loan.controllers.resources.PersonInfoRequest
import com.creditas.loan.controllers.resources.SuitableLoanPayload
import com.creditas.loan.controllers.resources.SuitableLoansResponse
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoanController {

    @GetMapping("/suitable-loans")
    fun getSuitableLoans(@RequestBody personInfoRequest: PersonInfoRequest): ResponseEntity<SuitableLoansResponse> {
        println("Received request to check suitable loans: $personInfoRequest")

        val mockResponse = SuitableLoansResponse(
            customer = "Daniel",
            loans = listOf(
                SuitableLoanPayload(
                    type = "Personal",
                    taxes = 4.0
                )
            )
        )

        return ResponseEntity(mockResponse, OK)
    }
}
