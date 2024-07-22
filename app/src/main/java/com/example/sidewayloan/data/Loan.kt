package com.example.sidewayloan.data

enum class LoanType {
    PERSONAL,
    HOUSING
}

data class Loan (
    val type: LoanType,
    val amount: Float,
    val interestRate: Float,
    val repaymentPeriod: Short,
    val startDateUnixTime: Long
)