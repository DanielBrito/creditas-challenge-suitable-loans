package com.creditas.loan.applications.handlers

import com.creditas.loan.domain.Customer
import com.creditas.loan.domain.Loan

interface LoanHandler {
    fun handle(customer: Customer, suitableLoans: MutableList<Loan>)
    fun isApplicable(customer: Customer): Boolean
}
