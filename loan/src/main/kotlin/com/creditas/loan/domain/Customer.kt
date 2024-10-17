package com.creditas.loan.domain

data class Customer(
    val name: String,
    val age: Int,
    val location: String,
    val income: Double
) {
    companion object {
        private const val AGE_LIMIT = 30
    }

    val livesInSP: Boolean
        get() = location == "SP"

    val isUnder30YO: Boolean
        get() = age < AGE_LIMIT
}
