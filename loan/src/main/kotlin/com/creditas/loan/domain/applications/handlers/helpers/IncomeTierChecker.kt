package com.creditas.loan.domain.applications.handlers.helpers

object IncomeTierChecker {
    private const val ZERO = 0.0
    private const val THREE_THOUSAND = 3000.0
    private const val FIVE_THOUSAND = 5000.0

    fun isTierOne(income: Double) =
        income > ZERO && income <= THREE_THOUSAND

    fun isTierTwo(income: Double) =
        income > THREE_THOUSAND && income < FIVE_THOUSAND

    fun isTierThree(income: Double) =
        income >= FIVE_THOUSAND
}
