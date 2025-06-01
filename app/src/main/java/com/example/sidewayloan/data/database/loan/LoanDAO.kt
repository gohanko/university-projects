package com.example.sidewayloan.data.database.loan

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDAO {
    @Upsert
    suspend fun upsertLoan(loan: Loan)

    @Delete
    suspend fun deleteLoan(loan: Loan)

    @Query("SELECT * FROM loan ORDER BY id ASC")
    fun getLoansOrderedByID(): Flow<List<Loan>>
}