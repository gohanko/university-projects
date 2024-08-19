package com.example.sidewayqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sidewayqr.ui.screens.ScanHistoryScreen.ScanHistoryScreen
import com.example.sidewayqr.ui.theme.SidewayQRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SidewayQRTheme {
                ScanHistoryScreen()
            }
        }
    }
}
