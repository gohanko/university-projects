package com.example.sidewayloan.data.database.loan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
)
