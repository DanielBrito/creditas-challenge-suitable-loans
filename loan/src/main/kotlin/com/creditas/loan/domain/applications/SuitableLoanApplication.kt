package com.creditas.loan.domain.applications

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import com.creditas.loan.domain.applications.handlers.LoanHandlerChain
import org.springframework.stereotype.Component

@Component
class SuitableLoanApplication(
    val loanHandlerChain: LoanHandlerChain
) {

    fun process(customer: Customer): List<Loan> {
        return loanHandlerChain.process(customer)
    }
}
