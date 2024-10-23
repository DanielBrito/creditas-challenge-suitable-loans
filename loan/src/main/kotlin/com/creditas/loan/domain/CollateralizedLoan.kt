package com.creditas.loan.domain

class CollateralizedLoan private constructor(
    override val type: String,
    override val taxes: Double
) : Loan {

    companion object {
        operator fun invoke(type: String = "Collateralized", taxes: Double = 3.0) =
            CollateralizedLoan(type, taxes)
    }
}
