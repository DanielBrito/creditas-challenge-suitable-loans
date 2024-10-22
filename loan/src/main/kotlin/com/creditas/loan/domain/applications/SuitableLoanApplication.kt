package com.creditas.loan.domain.applications

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.applications.handlers.LoanHandler
import org.springframework.stereotype.Component

@Component
class SuitableLoanApplication(
    val loanHandler: List<LoanHandler>
) {

    fun process(customer: Customer): List<Loan> {
        val suitableLoans = mutableListOf<Loan>()

        loanHandler.forEach { it.handle(customer, suitableLoans) }

        return suitableLoans
    }
}
