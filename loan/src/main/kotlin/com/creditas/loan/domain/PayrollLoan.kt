package com.creditas.loan.domain

class PayrollLoan private constructor(
    override val type: String,
    override val taxes: Double
) : Loan {

    companion object {
        operator fun invoke(type: String = "Payroll", taxes: Double = 2.0) =
            PayrollLoan(type, taxes)
    }
}
