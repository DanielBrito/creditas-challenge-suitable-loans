package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan
import org.springframework.stereotype.Component

@Component
class LoanHandlerChain(
    private val handlers: List<LoanHandler>
) {
    fun process(customer: Customer): List<Loan> {
        val eligibleLoans = mutableListOf<Loan>()

        for (handler in handlers) {
            handler.handle(customer)?.let { eligibleLoans.add(it) }
        }

        return eligibleLoans
    }
}
