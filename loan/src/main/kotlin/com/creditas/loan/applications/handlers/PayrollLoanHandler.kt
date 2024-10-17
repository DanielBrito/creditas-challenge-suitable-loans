package com.creditas.loan.applications.handlers

import com.creditas.loan.applications.handlers.helpers.IncomeTierChecker.isTierThree
import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PayrollLoan
import org.springframework.stereotype.Component

@Component
class PayrollLoanHandler : LoanHandler {

    override fun isApplicable(customer: Customer): Boolean {
        return isTierThree(customer.income)
    }

    override fun handle(customer: Customer, suitableLoans: MutableList<Loan>) {
        if (isApplicable(customer)) {
            suitableLoans.add(PayrollLoan())
        }
    }
}
