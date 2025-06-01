package com.example.sidewayloan.data.database.loan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.sidewayloan.data.AmortisationTableItem
import com.example.sidewayloan.utils.convertMillisToDate
import com.example.sidewayloan.utils.dateFormat
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val bigDecimalPrecision = 2

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
    var amount: BigDecimal = "0.00".toBigDecimal(),

    @ColumnInfo(name = "interestRate")
    var interestRate: BigDecimal = "0.00".toBigDecimal(),

    @ColumnInfo(name = "numberOfInstalment")
    var numberOfInstalment: Int = 0,

    @ColumnInfo(name = "startDateUnixTime")
    var startDateUnixTime: Long = 0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) {
    private fun getInterestRatePerHundred(): BigDecimal {
        val interestRatePerHundred: BigDecimal = interestRate
            .divide(100.toBigDecimal(), bigDecimalPrecision, RoundingMode.HALF_UP)
            .divide(12.toBigDecimal(), 10, RoundingMode.HALF_UP)

        return interestRatePerHundred
    }

    fun getMonthlyInstalment(): BigDecimal {
        val monthlyInstalment = when(type) {
            LoanType.PERSONAL -> {
                "1".toBigDecimal()
                    .plus(getInterestRatePerHundred()
                        .times(numberOfInstalment.toBigDecimal()))
                    .times(amount)
                    .divide(numberOfInstalment.run { toBigDecimal() }, bigDecimalPrecision, RoundingMode.HALF_UP)
            }
            LoanType.HOUSING -> {
                "1".toBigDecimal()
                    .add(getInterestRatePerHundred())
                    .pow(numberOfInstalment)
                    .times(getInterestRatePerHundred())
                    .times(amount)
                    .divide(
                        "1".toBigDecimal()
                            .plus(getInterestRatePerHundred())
                            .pow(numberOfInstalment)
                            .minus("1".toBigDecimal()),
                        bigDecimalPrecision, RoundingMode.HALF_UP)
            }
        }

        return monthlyInstalment
    }

    fun getLastPaymentDate(): String {
        val date = LocalDate.parse(
            convertMillisToDate(startDateUnixTime),
            DateTimeFormatter.ofPattern(dateFormat)
        ).plusMonths(numberOfInstalment.toLong())

        return date.toString()
    }

    fun getTotalAmountPaid(): BigDecimal {
        val totalAmountPaid = getMonthlyInstalment()
            .times(numberOfInstalment.toBigDecimal())
        return totalAmountPaid
    }

    private fun getPersonalLoanTableDataset(): List<AmortisationTableItem> {
        val tableDataset = mutableListOf<AmortisationTableItem>()

        var currentPrinciple = amount

        val monthlyInstalment = getMonthlyInstalment()
        val monthlyPrinciple = currentPrinciple.divide(numberOfInstalment.toBigDecimal(), 2, RoundingMode.HALF_UP)

        repeat(numberOfInstalment) { index ->
            val amortisationRow = AmortisationTableItem(
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

    private fun getHousingLoanTableDataset(): List<AmortisationTableItem> {
        val tableDataset = mutableListOf<AmortisationTableItem>()

        var currentAmount = amount
        repeat(numberOfInstalment) { index ->
            val monthlyRepayment = getMonthlyInstalment()
            val interestRate = interestRate
                .divide(100.toBigDecimal(), bigDecimalPrecision, RoundingMode.HALF_UP)
                .divide(12.toBigDecimal(), 10, RoundingMode.HALF_UP)

            val interestPaid = currentAmount.times(interestRate)
            val principlePaid = monthlyRepayment.minus(interestPaid)

            val amortisationTableItem = AmortisationTableItem(
                paymentNumber = index + 1,
                beginningBalance = currentAmount.setScale(2, RoundingMode.HALF_UP),
                monthlyRepayment = monthlyRepayment.setScale(2, RoundingMode.HALF_UP),
                interestPaid = interestPaid.setScale(2, RoundingMode.HALF_UP),
                principalPaid = principlePaid.setScale(2, RoundingMode.HALF_UP)
            )

            tableDataset.add(amortisationTableItem)
            currentAmount = currentAmount.minus(principlePaid).setScale(2, RoundingMode.HALF_UP)
        }

        return tableDataset
    }

    fun getAmortisationTableDataset(): List<AmortisationTableItem> {
        var amortisationTableDataset = listOf<AmortisationTableItem>()

        if (type == LoanType.PERSONAL) {
            amortisationTableDataset = getPersonalLoanTableDataset()
        }

        if (type == LoanType.HOUSING){
            amortisationTableDataset = getHousingLoanTableDataset()
        }

        return amortisationTableDataset
    }
}