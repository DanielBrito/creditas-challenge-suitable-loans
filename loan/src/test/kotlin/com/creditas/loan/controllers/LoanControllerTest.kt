package com.creditas.loan.controllers

import com.creditas.loan.controllers.resources.PersonInfoPayload
import com.creditas.loan.controllers.resources.PersonInfoRequest
import com.creditas.loan.controllers.resources.SuitableLoanPayload
import com.creditas.loan.controllers.resources.SuitableLoansResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity

internal class LoanControllerTest {
    private val loanController = LoanController()

    @Nested
    @DisplayName("given a request with a person info")
    inner class CheckSuitableLoans {
        private val personInfoRequest = PersonInfoRequest(
            customer = PersonInfoPayload(
                name = "Daniel",
                cpf = "123.456.789-10",
                age = 31,
                location = "SP",
                income = 3000.0
            )
        )
        private lateinit var result: ResponseEntity<SuitableLoansResponse>

        @BeforeEach
        fun act() {
            result = loanController.getSuitableLoans(personInfoRequest)
        }

        @Test
        fun `returns suitable loans`() {
            val suitableLoansResponse = SuitableLoansResponse(
                customer = "Daniel",
                loans = listOf(
                    SuitableLoanPayload(
                        type = "Personal",
                        taxes = 4.0
                    )
                )
            )

            assertThat(result.body).isEqualTo(suitableLoansResponse)
        }

        @Test
        fun `returns ok status code`() {
            assertThat(result.statusCode).isEqualTo(OK)
        }
    }
}
