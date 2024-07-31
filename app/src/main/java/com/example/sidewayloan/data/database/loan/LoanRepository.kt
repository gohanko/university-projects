package com.example.sidewayloan.data.database.loan

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanRepository (private val loanDAO: LoanDAO) {
    val allLoans = MutableLiveData<List<Loan>>()
    val foundLoan = MutableLiveData<Loan>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getLoansOrderedByID() {
        coroutineScope.launch(Dispatchers.IO) {
            loanDAO.getLoansOrderedByID()
        }
    }

    fun upsertLoan(newLoan: Loan) {
        coroutineScope.launch(Dispatchers.IO) {
            loanDAO.upsertLoan(newLoan)
        }
    }


}