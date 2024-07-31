package com.example.sidewayloan.data.database.loan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sidewayloan.data.AmortisationRow
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

@Entity
data class Loan (
    @ColumnInfo(name = "type")
    var type: LoanType = LoanType.HOUSING,

    @ColumnInfo(name = "amount")
    var amount: Float = 0.0f,

    @ColumnInfo(name = "interestRate")
    var interestRate: Float = 0.0f,

    @ColumnInfo(name = "numberOfInstalment")
    var numberOfInstalment: Int = 0,

    @ColumnInfo(name = "startDateUnixTime")
    var startDateUnixTime: Long = 0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
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
