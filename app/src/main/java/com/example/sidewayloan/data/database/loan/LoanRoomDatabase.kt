package com.example.sidewayloan.data.database.loan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [(Loan::class)],
    version = 1,
    exportSchema = false
)
abstract class LoanRoomDatabase : RoomDatabase() {
    abstract val dao: LoanDAO
}