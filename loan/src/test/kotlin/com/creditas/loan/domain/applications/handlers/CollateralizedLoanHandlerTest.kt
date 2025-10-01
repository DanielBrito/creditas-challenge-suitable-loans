package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CollateralizedLoanHandlerTest {
    private val collateralizedLoanHandler = CollateralizedLoanHandler()

    @Nested
    @DisplayName("given a customer")
    inner class HandleCollateralizedLoan {

        @Test
        fun `returns collateralized loan if income is from tier one and customer lives in SP and is under 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 29,
                location = "SP",
                income = 2000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo("Collateralized")
            assertThat(result?.taxes).isEqualTo(3.0)
        }

        @Test
        fun `returns collateralized loan if income is from tier two and customer lives in SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 41,
                location = "SP",
                income = 4000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo("Collateralized")
            assertThat(result?.taxes).isEqualTo(3.0)
        }

        @Test
        fun `returns collateralized loan if income is from tier three and customer is under 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "SP",
                income = 9000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNotNull
            assertThat(result?.type).isEqualTo("Collateralized")
            assertThat(result?.taxes).isEqualTo(3.0)
        }

        @Test
        fun `does not return collateralized loan if income is greater or equal to 5000 and age is over 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "SP",
                income = 9000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNull()
        }

        @Test
        fun `does not return collateralized loan if income is between 3000 and 5000 and location is not SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "CE",
                income = 4000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNull()
        }

        @Test
        fun `does not return collateralized loan if income is less than or equal to 3000 and age is over 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "SP",
                income = 2000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNull()
        }

        @Test
        fun `does not return collateralized loan if income is less than or equal to 3000 and location is not SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "CE",
                income = 2000.0
            )

            val result = collateralizedLoanHandler.handle(customer)

            assertThat(result).isNull()
        }
    }
}
