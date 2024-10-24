package com.creditas.loan.inbound.controllers.resources

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LoanResponseTest {

    @Test
    fun `returns proper value when getting customer`() {
        val suitableLoansResponse = SuitableLoansResponse(
            customer = "Daniel",
            loans = listOf(
                SuitableLoanPayload(
                    type = "Personal",
                    taxes = 4.0
                )
            )
        )

        val result = suitableLoansResponse.customer

        assertThat(result).isEqualTo("Daniel")
    }

    @Test
    fun `returns proper items when getting loans`() {
        val suitableLoansResponse = SuitableLoansResponse(
            customer = "Daniel",
            loans = listOf(
                SuitableLoanPayload(
                    type = "Personal",
                    taxes = 4.0
                )
            )
        )

        val result = suitableLoansResponse.loans

        assertThat(result).hasSize(1)
    }

    @Test
    fun `returns proper value when getting loan type`() {
        val suitableLoansResponse = SuitableLoansResponse(
            customer = "Daniel",
            loans = listOf(
                SuitableLoanPayload(
                    type = "Personal",
                    taxes = 4.0
                )
            )
        )

        val result = suitableLoansResponse.loans.first().type

        assertThat(result).isEqualTo("Personal")
    }

    @Test
    fun `returns proper value when getting loan taxes`() {
        val suitableLoansResponse = SuitableLoansResponse(
            customer = "Daniel",
            loans = listOf(
                SuitableLoanPayload(
                    type = "Personal",
                    taxes = 4.0
                )
            )
        )

        val result = suitableLoansResponse.loans.first().taxes

        assertThat(result).isEqualTo(4.0)
    }
}
