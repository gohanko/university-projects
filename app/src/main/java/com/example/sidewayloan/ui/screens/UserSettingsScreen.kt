package com.example.sidewayloan.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.navigation.MainRoute
import com.example.sidewayloan.ui.composables.BottomButton
import com.example.sidewayloan.ui.composables.date_picker_field.DatePickerField
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate
import kotlinx.coroutines.launch

@Composable
fun UserSettingsScreen(
    navHostController: NavHostController,
    userSettingsDataStore: DataStore<UserSettings>
) {
    var storedBirthday = userSettingsDataStore
        .data
        .collectAsState(
            initial = UserSettings()
        )
        .value
        .birthday ?: System.currentTimeMillis()

    val scope = rememberCoroutineScope()

    suspend fun setBirthday(birthdayUnix: Long) {
        userSettingsDataStore.updateData { it.copy(birthday = birthdayUnix) }
    }

    Column(
        Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = "Please select your birthdate",
            fontWeight = FontWeight.Medium,
            fontSize = 25.sp
        )

        DatePickerField(
            label = "Birthdate",
            modifier = Modifier.fillMaxWidth(),
            selectedDate = convertMillisToDate(storedBirthday),
            onSelectDate = {
                scope.launch {
                    setBirthday(convertDateToMillis(it))
                }
            }
        )

        BottomButton(
            label = "Back",
            onClick = {
                scope.launch {
                    navHostController.popBackStack()
                }
            }
        )
    }
}