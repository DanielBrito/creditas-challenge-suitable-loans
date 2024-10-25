package com.creditas.loan.inbound

import com.creditas.loan.LoanApplication
import helpers.FileLoader.readJsonResource
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
@SpringBootTest(classes = [LoanApplication::class])
internal class LoanControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("given a request with valid customer info")
    inner class ValidCustomerInfo {

        @Test
        fun `returns single suitable loan`() {
            val customerRequest = readJsonResource("json/requests", "first_customer_request")
            val suitableLoansResponse = readJsonResource("json/responses", "single_suitable_loan_response")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isOk() }
                content { json(suitableLoansResponse) }
            }
        }

        @Test
        fun `returns multiple suitable loans`() {
            val customerRequest = readJsonResource("json/requests", "second_customer_request")
            val suitableLoansResponse = readJsonResource("json/responses", "multiple_suitable_loans_response")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isOk() }
                content { json(suitableLoansResponse) }
            }
        }
    }

    @Nested
    @DisplayName("given a request with invalid customer info")
    inner class InvalidValidCustomerInfo {

        @Test
        fun `returns error message when customer is null`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_customer")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$.error", `is`("Invalid request payload."))
            }
        }

        @Test
        fun `returns error message when name is blank`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_name")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.name']", `is`("Name cannot be blank."))
            }
        }

        @Test
        fun `returns error message when cpf is blank`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_cpf")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.cpf']", `is`("CPF cannot be blank."))
            }
        }

        @Test
        fun `returns error message when cpf does not match pattern`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_cpf_pattern")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.cpf']", `is`("CPF should be in the format ###.###.###-##"))
            }
        }

        @Test
        fun `returns error message when age is less than 18`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_age")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.age']", `is`("Age must be greater than or equal to 18."))
            }
        }

        @Test
        fun `returns error message when location is not from brazil`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_brazil_location")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.location']", `is`("Invalid Brazil location code."))
            }
        }

        @Test
        fun `returns error message when income is less than or equal to 0`() {
            val customerRequest = readJsonResource("json/requests", "customer_request_invalid_income")

            mockMvc.post("/suitable-loans") {
                contentType = APPLICATION_JSON
                content = customerRequest
            }.andExpect {
                status { isBadRequest() }
            }.andExpect {
                jsonPath("$['customer.income']", `is`("Income must be greater than 0."))
            }
        }
    }
}
