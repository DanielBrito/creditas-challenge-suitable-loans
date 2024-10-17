package com.creditas.loan.domain

data class PersonalLoan(
    override val type: String = "Personal",
    override val taxes: Double = 4.0
) : Loan
