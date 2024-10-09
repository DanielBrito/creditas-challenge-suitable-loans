package com.creditas.loan.controllers.resources

data class SuitableLoansResponse(
    val customer: String,
    val loans: List<SuitableLoanPayload>
)

data class SuitableLoanPayload(
    val type: String,
    val taxes: Double
)
