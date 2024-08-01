package com.example.sidewayloan.ui.screens.user_settings_screen

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
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.navigation.MainRoute
import com.example.sidewayloan.ui.composables.date_picker_field.DatePickerField
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate
import kotlinx.coroutines.launch

@Composable
fun UserSettingsScreen(
    navHostController: NavHostController,
    userSettingsDataStore: DataStore<UserSettings>
) {
    val storedBirthday = userSettingsDataStore.data.collectAsState(
        initial = UserSettings()
    ).value.birthday ?: System.currentTimeMillis()

    var birthday by remember { mutableStateOf(convertMillisToDate(storedBirthday)) }
    val scope = rememberCoroutineScope()

    suspend fun setBirthday(birthdayUnix: Long) {
        userSettingsDataStore.updateData {
            it.copy(birthday = birthdayUnix)
        }
    }

    Column(
        Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Text("Please enter your birthdate")

        DatePickerField(
            modifier = Modifier.fillMaxWidth(),
            initialSelectedDate = birthday,
            onSelectDate = {
                birthday = it
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    scope.launch {
                        setBirthday(convertDateToMillis(birthday))
                        navHostController.navigate(MainRoute)
                    }

                    Log.d("Save Button", "CLICKED")
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}