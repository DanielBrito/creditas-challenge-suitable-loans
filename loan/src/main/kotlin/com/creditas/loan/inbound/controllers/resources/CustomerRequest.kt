package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.Customer

data class CustomerRequest(
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
    val name: String,
    val cpf: String,
    val age: Int,
    val location: String,
    val income: Double
)
