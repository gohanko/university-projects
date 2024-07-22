package com.example.sidewayloan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar
import com.example.sidewayloan.ui.theme.SidewayLoanTheme

class CalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SidewayLoanTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CalculatorTopAppBar()
                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                    }
                }
            }
        }
    }
}
