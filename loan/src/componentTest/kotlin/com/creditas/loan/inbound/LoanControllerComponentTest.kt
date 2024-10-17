package inbound

import com.creditas.loan.LoanApplication
import com.fasterxml.jackson.module.kotlin.jsonMapper
import helpers.FileLoader
import helpers.FileLoader.readJsonResource
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
@SpringBootTest(classes = [LoanApplication::class])
internal class LoanControllerComponentTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `given a request with customer info when processing it then returns single suitable loans`() {
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
    fun `given a request with customer info when processing it then returns multiple suitable loans`() {
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
