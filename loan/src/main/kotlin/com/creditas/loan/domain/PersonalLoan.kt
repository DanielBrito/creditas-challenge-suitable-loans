package com.creditas.loan.domain

class PersonalLoan private constructor(
    override val type: String,
    override val taxes: Double
) : Loan {

    companion object {
        operator fun invoke(type: String = "Personal", taxes: Double = 4.0) =
            PersonalLoan(type, taxes)
    }
}
