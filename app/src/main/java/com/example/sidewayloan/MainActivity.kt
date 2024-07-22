package com.example.sidewayloan

import com.example.sidewayloan.navigation.RootGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sidewayloan.theme.SidewayLoanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SidewayLoanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RootGraph(modifier = Modifier, navController)
                }
            }
        }
    }
}

fun calculateLoan(principle: Float, rateOfInterest: Float, numberOfInstallments: Int): Float {
    return (principle * (1 + (rateOfInterest * numberOfInstallments))) / numberOfInstallments
}