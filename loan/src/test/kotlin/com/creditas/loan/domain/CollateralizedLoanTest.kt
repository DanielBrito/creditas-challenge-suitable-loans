package com.creditas.loan.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CollateralizedLoanTest {

    @Test
    fun `returns collateralized when getting type`() {
        val loan = CollateralizedLoan()

        assertThat(loan.type).isEqualTo("Collateralized")
    }

    @Test
    fun `returns 3 when getting taxes`() {
        val loan = CollateralizedLoan()

        assertThat(loan.taxes).isEqualTo(3.0)
    }
}
