package com.example.sidewayloan.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.example.sidewayloan.data.BottomNavigationItem
import com.example.sidewayloan.navigation.History
import com.example.sidewayloan.navigation.Settings

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val items = listOf(
        BottomNavigationItem(
            route = History,
            text = "History",
            selectedIcon = Icons.AutoMirrored.Filled.Article,
            unselectedIcon = Icons.AutoMirrored.Outlined.Article
        ),
        BottomNavigationItem(
            route = Settings,
            text = "Settings",
            selectedIcon = Icons.Outlined.Settings,
            unselectedIcon = Icons.Filled.Settings
        )
    )

    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                label = {
                    Text(item.text)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.text
                    )
                }
            )
        }
    }
}