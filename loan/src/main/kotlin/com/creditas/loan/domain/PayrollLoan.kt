package com.creditas.loan.domain

data class PayrollLoan(
    override val type: String = "Payroll",
    override val taxes: Double = 2.0
) : Loan
