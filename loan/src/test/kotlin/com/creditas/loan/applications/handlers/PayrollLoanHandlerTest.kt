package com.creditas.loan.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PayrollLoan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PayrollLoanHandlerTest {
    private val payrollLoanHandler = PayrollLoanHandler()

    @Nested
    @DisplayName("given a customer and a list of suitable loans")
    inner class HandlePayrollLoan {
        private val expectedSuitableLoans = mutableListOf(PayrollLoan())

        @Test
        fun `adds payroll loan if income is from tier three`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            payrollLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEqualTo(expectedSuitableLoans)
        }

        @Test
        fun `does not add payroll loan if income is from tier one`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            payrollLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }

        @Test
        fun `does not add payroll loan if income is from tier two`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            payrollLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }
    }
}
