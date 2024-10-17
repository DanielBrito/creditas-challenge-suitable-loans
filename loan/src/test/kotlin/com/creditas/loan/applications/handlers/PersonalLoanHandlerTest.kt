package com.creditas.loan.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonalLoan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonalLoanHandlerTest {
    private val personalHandler = PersonalLoanHandler()

    @Nested
    @DisplayName("given a customer and a list of suitable loans")
    inner class HandlePersonalLoan {
        private val expectedSuitableLoans = mutableListOf(PersonalLoan())

        @Test
        fun `adds personal loan to list if income is less than or equal to 3000`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 2000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            personalHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEqualTo(expectedSuitableLoans)
        }

        @Test
        fun `adds personal loan to list if income is greater than 3000 or less than 5000`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 4000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            personalHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEqualTo(expectedSuitableLoans)
        }

        @Test
        fun `adds personal loan to list if income is greater or equal to 5000`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 6000.0
            )
            val suitableLoans = mutableListOf<Loan>()

            personalHandler.handle(customer, suitableLoans)

            assertThat(suitableLoans).isEqualTo(expectedSuitableLoans)
        }
    }
}
