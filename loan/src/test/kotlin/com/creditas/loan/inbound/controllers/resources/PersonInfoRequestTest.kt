package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.PersonInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonInfoRequestTest {

    @Nested
    @DisplayName("given a person info request")
    inner class PersonInfoRequestToPersonInfoDomain {

        @Test
        fun `maps to person info domain`() {
            val personInfoRequest = PersonInfoRequest(
                customer = PersonInfoPayload(
                    name = "Daniel",
                    cpf = "123.456.789-10",
                    age = 31,
                    location = "CE",
                    income = 3000.0
                )
            )
            val expectedPersonInfo = PersonInfo(
                name = "Daniel",
                age = 31,
                location = "CE",
                income = 3000.0
            )

            val result = personInfoRequest.toPersonInfo()

            assertThat(result).isEqualTo(expectedPersonInfo)
        }

        @Test
        fun `returns name from customer object`() {
            val personInfoRequest = PersonInfoRequest(
                customer = PersonInfoPayload(
                    name = "Daniel",
                    cpf = "123.456.789-10",
                    age = 31,
                    location = "CE",
                    income = 3000.0
                )
            )

            val result = personInfoRequest.getName()

            assertThat(result).isEqualTo("Daniel")
        }
    }
}
