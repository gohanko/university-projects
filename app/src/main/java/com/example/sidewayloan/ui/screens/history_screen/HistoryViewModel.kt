package com.example.sidewayloan.ui.screens.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.data.database.loan.LoanDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryViewModel(
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
