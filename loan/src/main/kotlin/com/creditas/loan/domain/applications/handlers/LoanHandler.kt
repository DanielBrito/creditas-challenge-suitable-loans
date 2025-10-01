package com.creditas.loan.domain.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan

interface LoanHandler {
    fun isApplicable(customer: Customer): Boolean
    fun handle(customer: Customer): Loan?
}
