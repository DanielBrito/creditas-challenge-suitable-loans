package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.CollateralizedLoan
import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierOne
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierThree
import com.creditas.loan.domain.applications.handlers.helpers.IncomeTierChecker.isTierTwo
import org.springframework.stereotype.Component

@Component
class CollateralizedLoanHandler : LoanHandler {

    override fun isApplicable(customer: Customer): Boolean {
        return (isTierOne(customer.income) && customer.livesInSP && customer.isUnder30YO) ||
            (isTierTwo(customer.income) && customer.livesInSP) ||
            (isTierThree(customer.income) && customer.isUnder30YO)
    }

    override fun handle(customer: Customer): Loan? {
        return if (isApplicable(customer)) CollateralizedLoan() else null
    }
}
