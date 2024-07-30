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
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate


@Preview
@Composable
fun PersonalDetailsScreen() {
    val currentTime = System.currentTimeMillis()
    var birthday by remember { mutableStateOf(convertMillisToDate(currentTime)) }

    fun setBirthday(birthdayUnix: Long) {

    }

    Column {
        Text("Please enter your birthdate")

        DateTextField(
            initialSelectedDate = birthday,
            onSelectDate = {
                birthday = it
            }
        )

        Box(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    setBirthday(convertDateToMillis(birthday))
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}