package com.creditas.loan.applications

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import org.springframework.stereotype.Component

@Component
class SuitableLoanApplication {

    companion object {
        const val THREE_THOUSAND = 3000.0
        const val FIVE_THOUSAND = 5000.0
    }

    fun process(customer: Customer): List<Loan> {
        val suitableLoans = mutableListOf<Loan>()

        if (incomeIsInLowerRange(customer.income) ||
            incomeIsInMiddleRange(customer.income) ||
            incomeIsInUpperRange(customer.income)
        ) {
            suitableLoans.add(Loan(type = "Personal", taxes = 4.0))
        }

        return suitableLoans
    }

    private fun incomeIsInLowerRange(income: Double): Boolean =
        income <= THREE_THOUSAND

    private fun incomeIsInMiddleRange(income: Double): Boolean =
        income > THREE_THOUSAND && income < FIVE_THOUSAND

    private fun incomeIsInUpperRange(income: Double): Boolean =
        income >= FIVE_THOUSAND
}
