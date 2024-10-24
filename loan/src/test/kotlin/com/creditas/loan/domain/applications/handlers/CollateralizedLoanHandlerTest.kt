package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CollateralizedLoanHandlerTest {
    private val collateralizedLoanHandler = CollateralizedLoanHandler()

    @Nested
    @DisplayName("given a customer and a list of suitable loans")
    inner class HandleCollateralizedLoan {

        @Test
        fun `adds collateralized loan if income is from tier one and customer lives in SP and is under 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 29,
                location = "SP",
                income = 2000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            val result = suitableLoans.first()

            assertThat(result.type).isEqualTo("Collateralized")
            assertThat(result.taxes).isEqualTo(3.0)
        }

        @Test
        fun `adds collateralized loan if income is from tier two and customer lives in SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 41,
                location = "SP",
                income = 4000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            val result = suitableLoans.first()

            assertThat(result.type).isEqualTo("Collateralized")
            assertThat(result.taxes).isEqualTo(3.0)
        }

        @Test
        fun `adds collateralized loan if income is from tier three and customer is under 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "SP",
                income = 9000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            val result = suitableLoans.first()

            assertThat(result.type).isEqualTo("Collateralized")
            assertThat(result.taxes).isEqualTo(3.0)
        }

        @Test
        fun `does not add collateralized loan if income is greater or equal to 5000 and age is over 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "SP",
                income = 9000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }

        @Test
        fun `does not add collateralized loan if income is between 3000 and 5000 and location is not SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "CE",
                income = 4000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }

        @Test
        fun `does not add collateralized loan if income is less than or equal to 3000 and age is over 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 40,
                location = "SP",
                income = 2000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }

        @Test
        fun `does not add collateralized loan if income is less than or equal to 3000 and location is not SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "CE",
                income = 2000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            collateralizedLoanHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEmpty()
        }
    }
}
