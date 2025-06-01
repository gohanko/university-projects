package com.example.sidewayloan.data.database.loan

import androidx.room.TypeConverter
import java.math.BigDecimal

class LoanConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}