package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.PayrollLoan
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class LoanHandlerChainTest {
    private val handler: PayrollLoanHandler = mockk(relaxed = true)
    private val loanHandlerChain = LoanHandlerChain(listOf(handler))

    @Nested
    @DisplayName("given a customer")
    inner class HandleChain {
        val customer = Customer(
            name = "Daniel",
            age = 30,
            location = "SP",
            income = 6000.0
        )

        @Test
        fun `adds loan to the list if it is eligible`() {
            val payrollLoan = PayrollLoan()

            every { handler.handle(customer) } returns payrollLoan

            val result = loanHandlerChain.process(customer)

            assertThat(result.first()).isEqualTo(payrollLoan)
        }

        @Test
        fun `does not add loan to the list if it is not eligible`() {
            every { handler.handle(customer) } returns null

            val result = loanHandlerChain.process(customer)

            assertThat(result).isEmpty()
        }
    }
}
