package com.creditas.loan.inbound.controllers

import com.creditas.loan.applications.SuitableLoanApplication
import com.creditas.loan.domain.PersonalLoan
import com.creditas.loan.inbound.controllers.resources.CustomerInfoPayload
import com.creditas.loan.inbound.controllers.resources.CustomerRequest
import com.creditas.loan.inbound.controllers.resources.SuitableLoanPayload
import com.creditas.loan.inbound.controllers.resources.SuitableLoansResponse
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity

internal class LoanControllerTest {
    private val suitableLoanApplication = mockk<SuitableLoanApplication>()
    private val loanController = LoanController(suitableLoanApplication)

    @Nested
    @DisplayName("given a request with some customer info")
    inner class GetSuitableLoans {
        private val customerRequest = CustomerRequest(
            customer = CustomerInfoPayload(
                name = "Daniel",
                cpf = "123.456.789-10",
                age = 31,
                location = "SP",
                income = 3000.0
            )
        )
        private lateinit var result: ResponseEntity<SuitableLoansResponse>

        @BeforeEach
        fun arrangeAndAct() {
            every {
                suitableLoanApplication.process(customerRequest.toCustomer())
            } returns listOf(PersonalLoan(type = "Personal", taxes = 4.0))

            result = loanController.getSuitableLoans(customerRequest)
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
