package com.example.sidewayloan.data.database.loan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoanViewModel(
    private val loanDao: LoanDAO
): ViewModel() {
    fun getLoansOrderedByID(): Flow<List<Loan>> {
        return loanDao.getLoansOrderedByID()
    }

    fun upsertLoan(loan: Loan) {
        viewModelScope.launch {
            loanDao.upsertLoan(loan)
            getLoansOrderedByID()
        }
    }
}
