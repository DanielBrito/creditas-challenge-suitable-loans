package com.creditas.loan.controllers.resources

data class PersonInfoRequest(
    val customer: PersonInfoPayload
)

data class PersonInfoPayload(
    val name: String,
    val cpf: String,
    val age: Int,
    val location: String,
    val income: Double
)
