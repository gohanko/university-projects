package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sidewayloan.ui.composables.DateTextField
import com.example.sidewayloan.utils.convertMillisToDate

@Composable
fun BirthdayField() {
    OutlinedTextField(
        label = { Text(text = "Birthday") },
        value = "",
        onValueChange = {}
    )
}

@Preview
@Composable
fun PersonalDetailsScreen() {
    val currentTime = System.currentTimeMillis()
    var birthdate by remember { mutableStateOf(convertMillisToDate(currentTime)) }

    Column {
        Text("Please enter your birthdate")

        DateTextField(
            initialSelectedDate = birthdate,
            onSelectDate = {
                birthdate = it
            }
        )

        Box(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                }
            ) {
                Text(text = "Save")
            }
        }
    }
}