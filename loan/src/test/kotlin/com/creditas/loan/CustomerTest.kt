package com.creditas.loan

import com.creditas.loan.domain.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CustomerTest {

    @Nested
    @DisplayName("given a customer location")
    inner class Location {

        @Test
        fun `returns true if the customer lives in SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "SP",
                income = 3000.0
            )

            val result = customer.livesInSP

            assertThat(result).isTrue()
        }

        @Test
        fun `returns false if the customer does not live in SP`() {
            val customer = Customer(
                name = "Daniel",
                age = 30,
                location = "CE",
                income = 3000.0
            )

            val result = customer.livesInSP

            assertThat(result).isFalse()
        }
    }

    @Nested
    @DisplayName("given a customer age")
    inner class Age {

        @Test
        fun `returns true if the customer is under 30 YO`() {
            val customer = Customer(
                name = "Daniel",
                age = 25,
                location = "SP",
                income = 3000.0
            )

            val result = customer.isUnder30YO

            assertThat(result).isTrue()
        }

        @Test
        fun `returns false if the customer is 30 YO or more`() {
            val customer = Customer(
                name = "Daniel",
                age = 35,
                location = "SP",
                income = 3000.0
            )

            val result = customer.isUnder30YO

            assertThat(result).isFalse()
        }
    }
}