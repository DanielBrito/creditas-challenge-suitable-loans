package com.creditas.loan.inbound.controllers

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

class GlobalExceptionHandlerTest {

    private val globalExceptionHandler = GlobalExceptionHandler()
    private val methodParameter = mockk<MethodParameter>(relaxed = true)

    @Nested
    @DisplayName("given a validation exception")
    inner class ValidationExceptions {

        @Test
        fun `handles when there is a single field error`() {
            val fieldError = FieldError("objectName", "fieldName", "default message")
            val bindingResult = mockk<BindingResult>()
            val exception = MethodArgumentNotValidException(methodParameter, bindingResult)

            every { bindingResult.fieldErrors } returns listOf(fieldError)

            val response = globalExceptionHandler.handleValidationExceptions(exception)

            assertThat(response.statusCode).isEqualTo(BAD_REQUEST)
            assertThat(response.body).isEqualTo(mapOf("fieldName" to "default message"))
        }

        @Test
        fun `handles when there are multiple field errors`() {
            val fieldError1 = FieldError("objectName", "field1", "error message 1")
            val fieldError2 = FieldError("objectName", "field2", "error message 2")
            val bindingResult = mockk<BindingResult>()
            val exception = MethodArgumentNotValidException(methodParameter, bindingResult)

            every { bindingResult.fieldErrors } returns listOf(fieldError1, fieldError2)

            val response = globalExceptionHandler.handleValidationExceptions(exception)

            assertThat(response.statusCode).isEqualTo(BAD_REQUEST)
            assertThat(response.body).isEqualTo(mapOf("field1" to "error message 1", "field2" to "error message 2"))
        }

        @Test
        fun `handles when there are no field errors`() {
            val bindingResult = mockk<BindingResult>()
            val exception = MethodArgumentNotValidException(methodParameter, bindingResult)

            every { bindingResult.fieldErrors } returns emptyList()

            val response = globalExceptionHandler.handleValidationExceptions(exception)

            assertThat(response.statusCode).isEqualTo(BAD_REQUEST)
            assertThat(response.body).isEmpty()
        }
    }

    @Nested
    @DisplayName("given a json parse exception")
    inner class JsonParseException {

        @Test
        fun `handles when failing to parse payload`() {
            val exception = mockk<HttpMessageNotReadableException>()

            every { exception.localizedMessage } returns "HttpMessageNotReadableException: Failed to parse JSON."

            val response = globalExceptionHandler.handleJsonParseException(exception)

            assertThat(response.statusCode).isEqualTo(BAD_REQUEST)
            assertThat(response.body).isEqualTo(mapOf("error" to "Invalid request payload."))
        }
    }
}
