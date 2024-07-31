package com.example.sidewayloan.data

import com.example.sidewayloan.utils.convertMillisToDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.pow

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
    var amount: Float = 0.0f,
    var interestRate: Float = 0.0f,
    var numberOfInstalment: Int = 0,
    var startDateUnixTime: Long = 0,
) {
    fun getMonthlyInstalment(): Float {
        val monthlyInstalment = when(type) {
            LoanType.PERSONAL -> (amount * (1 + (interestRate * numberOfInstalment))) / numberOfInstalment
            LoanType.HOUSING -> (
                    amount * interestRate * (1 + interestRate).pow(numberOfInstalment)) / (1 + interestRate
            ).pow(numberOfInstalment) - 1
        }

        return monthlyInstalment
    }

    fun getLastPaymentDate(): String {
        val date = LocalDate.parse(
            convertMillisToDate(startDateUnixTime),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        ).plusMonths(numberOfInstalment.toLong())

        return date.toString()
    }

    fun getTotalAmountPaid(): Float {
        val totalAmountPaid = getMonthlyInstalment() * numberOfInstalment
        return totalAmountPaid
    }

    fun getTableDataset(): List<AmortisationRow> {
        val tableDataset = mutableListOf<AmortisationRow>()

        var currentPrinciple = amount

        val monthlyInstalment = getMonthlyInstalment()
        val monthlyPrinciple = currentPrinciple / numberOfInstalment

        repeat(numberOfInstalment) { index ->
            val amortisationRow = AmortisationRow(
                paymentNumber = index + 1,
                beginningBalance = currentPrinciple,
                interestPaid = (monthlyInstalment - monthlyPrinciple),
                monthlyRepayment = monthlyInstalment,
                principalPaid = monthlyPrinciple
            )

            tableDataset.add(amortisationRow)

            currentPrinciple -= monthlyInstalment
        }

        return tableDataset
    }
}
