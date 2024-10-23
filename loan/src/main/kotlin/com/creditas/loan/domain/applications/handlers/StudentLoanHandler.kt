package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PersonalLoan
import com.creditas.loan.domain.StudentLoan
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierOne
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierThree
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierTwo
import org.springframework.stereotype.Component

@Component
class StudentLoanHandler : LoanHandler {

    override fun isApplicable(customer: Customer): Boolean {
        return isTierOne(customer.income)
    }

    override fun handle(customer: Customer, suitableLoans: MutableList<Loan>) {
        if (isApplicable(customer)) {
            suitableLoans.add(StudentLoan())
        }
    }
}
