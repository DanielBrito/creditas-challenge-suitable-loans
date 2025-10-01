package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.PayrollLoan
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierThree
import org.springframework.stereotype.Component

@Component
class PayrollLoanHandler : LoanHandler {

    override fun isApplicable(customer: Customer): Boolean {
        return isTierThree(customer.income)
    }

    override fun handle(customer: Customer): Loan? {
        return if (isApplicable(customer)) PayrollLoan() else null
    }
}
