package com.creditas.loan.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PayrollLoanTest {

    @Test
    fun `returns payroll when getting type`() {
        val loan = PayrollLoan()

        assertThat(loan.type).isEqualTo("Payroll")
    }

    @Test
    fun `returns 2 when getting taxes`() {
        val loan = PayrollLoan()

        assertThat(loan.taxes).isEqualTo(2.0)
    }
}
