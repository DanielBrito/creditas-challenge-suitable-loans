package com.creditas.loan.domain.applications

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.PersonalLoan
import com.creditas.loan.domain.applications.handlers.LoanHandlerChain
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SuitableLoanApplicationTest {
    private val loanHandlerChain = mockk<LoanHandlerChain>(relaxed = true)
    private val suitableLoanApplication = SuitableLoanApplication(loanHandlerChain)

    @Nested
    @DisplayName("given some customer info")
    inner class ProcessSuitableLoans {

        @Test
        fun `returns suitable loans`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "SP",
                income = 2000.0
            )
            val personalLoan = PersonalLoan()

            every { loanHandlerChain.process(customer) } returns listOf(personalLoan)

            val result = suitableLoanApplication.process(customer)

            assertThat(result).containsExactly(personalLoan)
        }
    }
}
