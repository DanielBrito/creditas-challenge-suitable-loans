package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.PersonalLoan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonalLoanHandlerTest {
    private val personalHandler = PersonalLoanHandler()

    @Nested
    @DisplayName("given a customer")
    inner class HandlePersonalLoan {
        private val expectedSuitableLoan = PersonalLoan()

        @Test
        fun `returns personal loan if income is from tier one`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )

            val result = personalHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo(expectedSuitableLoan.type)
            assertThat(result?.taxes).isEqualTo(expectedSuitableLoan.taxes)
        }

        @Test
        fun `returns personal loan if income is from tier two`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )

            val result = personalHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo(expectedSuitableLoan.type)
            assertThat(result?.taxes).isEqualTo(expectedSuitableLoan.taxes)
        }

        @Test
        fun `returns personal loan if income is from tier three`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )

            val result = personalHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo(expectedSuitableLoan.type)
            assertThat(result?.taxes).isEqualTo(expectedSuitableLoan.taxes)
        }

        @Test
        fun `does not return personal loan if income is not from any tier`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = -1.0
            )

            val result = personalHandler.handle(customer)

            assertThat(result).isNull()
        }
    }

    @Nested
    @DisplayName("given a customer")
    inner class IsApplicable {

        @Test
        fun `returns true when income is from tier one`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )

            val result = personalHandler.isApplicable(customer)

            assertThat(result).isTrue()
        }

        @Test
        fun `returns true when income is from tier two`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )

            val result = personalHandler.isApplicable(customer)

            assertThat(result).isTrue()
        }

        @Test
        fun `returns true when income is from tier three`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )

            val result = personalHandler.isApplicable(customer)

            assertThat(result).isTrue()
        }

        @Test
        fun `returns false when income is from none of the tiers`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 0.0
            )

            val result = personalHandler.isApplicable(customer)

            assertThat(result).isFalse()
        }
    }
}
