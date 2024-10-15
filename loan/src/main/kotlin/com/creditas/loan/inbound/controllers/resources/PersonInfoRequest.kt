package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.PersonInfo

data class PersonInfoRequest(
    val customer: PersonInfoPayload
) {
    fun toPersonInfo() = PersonInfo(
        name = customer.name,
        age = customer.age,
        location = customer.location,
        income = customer.income
    )

    fun getName() = customer.name
}
data class PersonInfoPayload(
    val name: String,
    val cpf: String,
    val age: Int,
    val location: String,
    val income: Double
)
