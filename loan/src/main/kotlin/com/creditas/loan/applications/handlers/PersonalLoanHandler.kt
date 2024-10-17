package com.creditas.loan.applications.handlers

import com.creditas.loan.applications.handlers.helpers.IncomeTierChecker.isTierOne
import com.creditas.loan.applications.handlers.helpers.IncomeTierChecker.isTierThree
import com.creditas.loan.applications.handlers.helpers.IncomeTierChecker.isTierTwo
import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonalLoan
import org.springframework.stereotype.Component

@Component
class PersonalLoanHandler : LoanHandler {

    override fun isApplicable(customer: Customer): Boolean {
        return customer.income.let { isTierOne(it) || isTierTwo(it) || isTierThree(it) }
    }

    override fun handle(customer: Customer, suitableLoans: MutableList<Loan>) {
        if (isApplicable(customer)) {
            suitableLoans.add(PersonalLoan())
        }
    }
}
