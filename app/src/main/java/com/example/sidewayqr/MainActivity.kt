package com.example.sidewayqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.screens.scan_history.ScanHistoryScreen
import com.example.sidewayqr.ui.theme.SidewayQRTheme

class MainActivity : ComponentActivity() {
    private val sidewayQRAPIService = SidewayQRAPIService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SidewayQRTheme {
                ScanHistoryScreen(sidewayQRAPIService)
            }
        }
    }
}
