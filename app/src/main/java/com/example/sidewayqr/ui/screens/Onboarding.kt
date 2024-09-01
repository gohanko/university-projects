package com.example.sidewayqr.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sidewayqr.data.datastore.CookieRepository
import kotlinx.coroutines.runBlocking

@Composable
fun Onboarding(
    navHostController: NavHostController,
    cookieRepository: CookieRepository,
) {
    runBlocking {
        val cookie = cookieRepository.getCookie()

        if (cookie.isNotBlank()) {
            navHostController.navigate("scan_history_screen")
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 96.dp, horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "To",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "SidewayQR",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navHostController.navigate("login_screen")
                    }
                ) {
                    Text("Login")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navHostController.navigate("register_screen")
                    }
                ) {
                    Text("Register")
                }
            }
        }
    }
}