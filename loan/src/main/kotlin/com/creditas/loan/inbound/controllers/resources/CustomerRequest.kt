package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.Customer
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

data class CustomerRequest(
    @field:Valid
    @field:NotNull
    val customer: CustomerInfoPayload
) {
    fun toCustomer() = Customer(
        name = customer.name,
        age = customer.age,
        location = customer.location,
        income = customer.income
    )

    fun getName() = customer.name
}

data class CustomerInfoPayload(
    @field:NotBlank(message = "Name cannot be blank.")
    val name: String,

    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\$",
        message = "CPF should be in the format ###.###.###-##"
    )
    val cpf: String,

    @field:Min(value = 18, message = "Age must be greater than or equal to 18.")
    val age: Int,

    @field:Pattern(
        regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$",
        message = "Invalid Brazil location code."
    )
    val location: String,

    @field:Positive(message = "Income must be greater than 0.")
    val income: Double
)
