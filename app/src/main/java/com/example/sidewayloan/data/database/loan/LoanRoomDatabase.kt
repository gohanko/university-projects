package com.example.sidewayloan.data.database.loan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [(Loan::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(LoanConverter::class)
abstract class LoanRoomDatabase : RoomDatabase() {
    abstract val dao: LoanDAO
}