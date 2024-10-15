package com.creditas.loan.applications

import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SuitableLoanApplicationTest {
    private val suitableLoanApplication = SuitableLoanApplication()

    @Nested
    @DisplayName("given some person info")
    inner class ProcessSuitableLoans {

        @Test
        fun `returns personal loan if income is less than or equal to 3000`() {
            val personInfo = PersonInfo(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )
            val expectedSuitableLoan = listOf(
                Loan(
                    type = "Personal",
                    taxes = 4.0
                )
            )

            val result = suitableLoanApplication.process(personInfo)

            assertThat(result).isEqualTo(expectedSuitableLoan)
        }

        @Test
        fun `returns personal loan if income is greater than 3000 or less than 5000`() {
            val personInfo = PersonInfo(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )
            val expectedSuitableLoan = listOf(
                Loan(
                    type = "Personal",
                    taxes = 4.0
                )
            )

            val result = suitableLoanApplication.process(personInfo)

            assertThat(result).isEqualTo(expectedSuitableLoan)
        }

        @Test
        fun `returns personal loan if income is greater or equal to 5000`() {
            val personInfo = PersonInfo(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )
            val expectedSuitableLoan = listOf(
                Loan(
                    type = "Personal",
                    taxes = 4.0
                )
            )

            val result = suitableLoanApplication.process(personInfo)

            assertThat(result).isEqualTo(expectedSuitableLoan)
        }
    }
}
