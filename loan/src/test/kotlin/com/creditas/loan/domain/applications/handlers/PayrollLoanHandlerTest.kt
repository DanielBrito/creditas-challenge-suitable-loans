package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.PayrollLoan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PayrollLoanHandlerTest {
    private val payrollLoanHandler = PayrollLoanHandler()

    @Nested
    @DisplayName("given a customer")
    inner class HandlePayrollLoan {
        private val expectedSuitableLoan = PayrollLoan()

        @Test
        fun `returns payroll loan if income is from tier three`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )

            val result = payrollLoanHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo(expectedSuitableLoan.type)
            assertThat(result?.taxes).isEqualTo(expectedSuitableLoan.taxes)
        }

        @Test
        fun `does not return payroll loan if income is from tier one`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )

            val result = payrollLoanHandler.handle(customer)

            assertThat(result).isNull()
        }

        @Test
        fun `does not return payroll loan if income is from tier two`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )

            val result = payrollLoanHandler.handle(customer)

            assertThat(result).isNull()
        }
    }
}
