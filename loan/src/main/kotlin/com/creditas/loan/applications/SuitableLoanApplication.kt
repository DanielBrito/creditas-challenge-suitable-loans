package com.creditas.loan.applications

import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonInfo
import org.springframework.stereotype.Component

@Component
class SuitableLoanApplication {

    companion object {
        const val THREE_THOUSAND = 3000.0
        const val FIVE_THOUSAND = 5000.0
    }

    fun process(personInfo: PersonInfo): List<Loan> {
        val suitableLoans = mutableListOf<Loan>()

        if (incomeIsInLowerRange(personInfo.income) ||
            incomeIsInMiddleRange(personInfo.income) ||
            incomeIsInUpperRange(personInfo.income)
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
