package com.creditas.loan.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan

interface LoanHandler {
    fun isApplicable(customer: Customer): Boolean
    fun handle(customer: Customer, suitableLoans: MutableList<Loan>)
}
