package com.creditas.loan.inbound.controllers.resources

import com.creditas.loan.domain.Loan

data class SuitableLoansResponse(
    val customer: String,
    val loans: List<SuitableLoanPayload>
) {
    companion object {
        fun from(customerName: String, suitableLoans: List<Loan>) =
            SuitableLoansResponse(
                customer = customerName,
                loans = suitableLoans.map {
                    SuitableLoanPayload(
                        type = it.type,
                        taxes = it.taxes
                    )
                }
            )
    }
}
data class SuitableLoanPayload(
    val type: String,
    val taxes: Double
)
