package com.creditas.loan.domain

data class CollateralizedLoan(
    override val type: String = "Collateralized",
    override val taxes: Double = 3.0
) : Loan
