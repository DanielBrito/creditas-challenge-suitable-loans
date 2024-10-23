package com.creditas.loan.domain

data class StudentLoan(
    override val type: String = "Student",
    override val taxes: Double = 1.0
) : Loan
