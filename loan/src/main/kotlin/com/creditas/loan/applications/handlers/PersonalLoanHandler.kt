package com.creditas.loan.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonalLoan
import org.springframework.stereotype.Component

@Component
class PersonalLoanHandler : LoanHandler {

    companion object {
        const val THREE_THOUSAND = 3000.0
        const val FIVE_THOUSAND = 5000.0
    }

    override fun isApplicable(customer: Customer): Boolean {
        return incomeIsInLowerRange(customer.income) ||
            incomeIsInMiddleRange(customer.income) ||
            incomeIsInUpperRange(customer.income)
    }

    override fun handle(customer: Customer, suitableLoans: MutableList<Loan>) {
        if (isApplicable(customer)) {
            suitableLoans.add(PersonalLoan())
        }
    }

    private fun incomeIsInLowerRange(income: Double): Boolean =
        income <= THREE_THOUSAND

    private fun incomeIsInMiddleRange(income: Double): Boolean =
        income > THREE_THOUSAND && income < FIVE_THOUSAND

    private fun incomeIsInUpperRange(income: Double): Boolean =
        income >= FIVE_THOUSAND
}
