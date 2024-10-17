package com.creditas.loan.applications

import com.creditas.loan.applications.handlers.LoanHandler
import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import org.springframework.stereotype.Component

@Component
class SuitableLoanApplication(
    val loanHandler: LoanHandler
) {

    fun process(customer: Customer): List<Loan> {
        val suitableLoans = mutableListOf<Loan>()

        loanHandler.handle(customer, suitableLoans)

        return suitableLoans
    }
}
