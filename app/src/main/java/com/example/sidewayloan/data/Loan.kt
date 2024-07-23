package com.example.sidewayloan.data

import java.math.BigDecimal

enum class LoanType {
    PERSONAL("Personal Loan"),
    HOUSING("Housing Loan");

    private var printableName: String = ""

    constructor()

    constructor(printableName: String) {
        this.printableName = printableName
    }

    fun getPrintable(): String {
        return this.printableName
    }
}

data class Loan (
    var type: LoanType = LoanType.HOUSING,
    var amount: BigDecimal = "0.00".toBigDecimal(),
    var interestRate: Float = 0.0f,
    var repaymentPeriod: Short = 0,
    var startDateUnixTime: Long = 0,
)
