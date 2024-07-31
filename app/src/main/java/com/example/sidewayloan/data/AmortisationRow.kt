package com.example.sidewayloan.data

import java.math.BigDecimal

data class AmortisationRow(
    val paymentNumber: Int,
    val beginningBalance: Float,
    val monthlyRepayment: Float,
    val interestPaid: Float,
    val principalPaid: Float
)