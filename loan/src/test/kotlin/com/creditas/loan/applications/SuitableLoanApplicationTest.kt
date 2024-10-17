package com.creditas.loan.applications

import com.creditas.loan.applications.handlers.PersonalLoanHandler
import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonalLoan
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SuitableLoanApplicationTest {
    private val personalLoanHandler = mockk<PersonalLoanHandler>(relaxed = true)
    private val suitableLoanApplication = SuitableLoanApplication(listOf(personalLoanHandler))

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

            val suitableLoansSlot = slot<MutableList<Loan>>()

            every { personalLoanHandler.handle(customer, capture(suitableLoansSlot)) } answers {
                suitableLoansSlot.captured.add(personalLoan)
            }

            val result = suitableLoanApplication.process(customer)

            assertThat(result).containsExactly(personalLoan)
        }
    }
}
