package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CustomerRequestTest {

    @Nested
    @DisplayName("given a customer request")
    inner class CustomerRequestToCustomerDomain {

        @Test
        fun `maps to customer domain`() {
            val customerRequest = CustomerRequest(
                customer = CustomerInfoPayload(
                    name = "Daniel",
                    cpf = "123.456.789-10",
                    age = 31,
                    location = "CE",
                    income = 3000.0
                )
            )
            val expectedCustomer = Customer(
                name = "Daniel",
                age = 31,
                location = "CE",
                income = 3000.0
            )

            val result = customerRequest.toCustomer()

            assertThat(result).isEqualTo(expectedCustomer)
        }

        @Test
        fun `returns name from customer object`() {
            val customerRequest = CustomerRequest(
                customer = CustomerInfoPayload(
                    name = "Daniel",
                    cpf = "123.456.789-10",
                    age = 31,
                    location = "CE",
                    income = 3000.0
                )
            )

            val result = customerRequest.getName()

            assertThat(result).isEqualTo("Daniel")
        }

        @Test
        fun `returns proper value when getting customer cpf`() {
            val customerRequest = CustomerRequest(
                customer = CustomerInfoPayload(
                    name = "Daniel",
                    cpf = "123.456.789-10",
                    age = 31,
                    location = "CE",
                    income = 3000.0
                )
            )

            val result = customerRequest.customer.cpf

            assertThat(result).isEqualTo("123.456.789-10")
        }
    }
}
