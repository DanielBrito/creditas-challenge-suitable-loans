package com.creditas.loan.applications

import com.creditas.loan.applications.handlers.LoanHandler
import com.creditas.loan.domain.CollateralizedLoan
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
    private val loanHandler = mockk<LoanHandler>(relaxed = true)
    private val suitableLoanApplication = SuitableLoanApplication(loanHandler)

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
            val suitableLoansSlot = slot<MutableList<Loan>>()
            val expectedSuitableLoans = mutableListOf(PersonalLoan(), CollateralizedLoan())

            every { loanHandler.handle(customer, capture(suitableLoansSlot)) } answers {
                suitableLoansSlot.captured.add(PersonalLoan())
                suitableLoansSlot.captured.add(CollateralizedLoan())
            }

            val result = suitableLoanApplication.process(customer)

            assertThat(result).isEqualTo(expectedSuitableLoans)
        }
    }
}
