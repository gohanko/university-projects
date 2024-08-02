package com.example.sidewayloan.utils

import android.util.Log
import com.example.sidewayloan.data.AmortisationItem
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.data.database.loan.LoanType
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.pow

val bigDecimalPrecision = 2

fun getMonthlyInstalment(
    loan: Loan
): BigDecimal {
    val interestRatePerHundred = loan.interestRate
        .divide(100.toBigDecimal(), bigDecimalPrecision, RoundingMode.HALF_UP)
        .divide(12.toBigDecimal(), 10, RoundingMode.HALF_UP)

    val monthlyInstalment = when(loan.type) {
        LoanType.PERSONAL ->
            "1".toBigDecimal()
                .plus(interestRatePerHundred
                        .times(loan.numberOfInstalment.toBigDecimal()))
                .times(loan.amount)
            .divide(loan.numberOfInstalment.toBigDecimal(), bigDecimalPrecision, RoundingMode.HALF_UP)
        LoanType.HOUSING ->
            "1".toBigDecimal()
                .add(interestRatePerHundred)
                .pow(loan.numberOfInstalment)
                .times(interestRatePerHundred)
                .times(loan.amount)
                .divide(
                    "1".toBigDecimal()
                        .plus(interestRatePerHundred)
                        .pow(loan.numberOfInstalment)
                        .minus("1".toBigDecimal()),
                    bigDecimalPrecision, RoundingMode.HALF_UP)
    }

    return monthlyInstalment
}

fun getLastPaymentDate(loan: Loan): String {
    val date = LocalDate.parse(
        convertMillisToDate(loan.startDateUnixTime),
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    ).plusMonths(loan.numberOfInstalment.toLong())

    return date.toString()
}

fun getTotalAmountPaid(loan: Loan): BigDecimal {
    val totalAmountPaid = getMonthlyInstalment(loan).times(loan.numberOfInstalment.toBigDecimal())
    return totalAmountPaid
}

fun getPersonalLoanTableDataset(loan: Loan): List<AmortisationItem> {
    val tableDataset = mutableListOf<AmortisationItem>()

    var currentPrinciple = loan.amount

    val monthlyInstalment = getMonthlyInstalment(loan)
    val monthlyPrinciple = currentPrinciple.divide(loan.numberOfInstalment.toBigDecimal(), 2, RoundingMode.HALF_UP)

    repeat(loan.numberOfInstalment) { index ->
        val amortisationRow = AmortisationItem(
            paymentNumber = index + 1,
            beginningBalance = currentPrinciple,
            interestPaid = monthlyInstalment.subtract(monthlyPrinciple),
            monthlyRepayment = monthlyInstalment,
            principalPaid = monthlyPrinciple
        )

        tableDataset.add(amortisationRow)

        currentPrinciple = currentPrinciple.subtract(monthlyPrinciple)
    }

    return tableDataset
}

fun getHousingLoanTableDataset(loan: Loan): List<AmortisationItem> {
    val tableDataset = mutableListOf<AmortisationItem>()

    var currentAmount = loan.amount
    repeat(loan.numberOfInstalment) { index ->
        val monthlyRepayment = getMonthlyInstalment(loan)
        val interestRate = loan.interestRate
            .divide(100.toBigDecimal(), bigDecimalPrecision, RoundingMode.HALF_UP)
            .divide(12.toBigDecimal(), 10, RoundingMode.HALF_UP)

        val interestPaid = currentAmount.times(interestRate)
        val principlePaid = monthlyRepayment.minus(interestPaid)

        val amortisationItem = AmortisationItem(
            paymentNumber = index + 1,
            beginningBalance = currentAmount.setScale(2, RoundingMode.HALF_UP),
            monthlyRepayment = monthlyRepayment.setScale(2, RoundingMode.HALF_UP),
            interestPaid = interestPaid.setScale(2, RoundingMode.HALF_UP),
            principalPaid = principlePaid.setScale(2, RoundingMode.HALF_UP)
        )

        tableDataset.add(amortisationItem)
        currentAmount = currentAmount.minus(principlePaid).setScale(2, RoundingMode.HALF_UP)
    }

    return tableDataset
}