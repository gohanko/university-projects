package com.example.sidewayloan.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sidewayloan.R
import com.example.sidewayloan.ui.theme.primaryLight

@Composable
fun List(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        content()
    }
}

@Composable
fun ListItem(
    icon: ImageVector,
    label: String,
    description: String,
    onClick: () -> Unit,
) {
    Row (
        modifier = Modifier
            .clickable(onClick = { onClick() })
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .height(45.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(45.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = icon,
                    contentDescription = "List item icon",
                )
            }

            Column {
                Text(
                    text = label,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )

                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
            }
        }

    }
}

@Composable
fun SettingsScreen(
    openUserSettings: () -> Unit
) {
    List {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "  ╱|、\n" +
                        "(˚ˎ 。7\n" +
                        " |、˜〵\n" +
                        " じしˍ,)ノ"
            )
        }

        HorizontalDivider()

        ListItem(
            icon = Icons.Outlined.EditCalendar,
            label = "Edit Birthday",
            description = "Edit your birthday",
            onClick = { openUserSettings() }
        )

        ListItem(
            icon = Icons.Outlined.Info,
            label = "About",
            description = "",
            onClick = {}
        )

        val uriHandler = LocalUriHandler.current
        ListItem(
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            label = "Help",
            description = "Redirects you to CatGPT for all your meowy questions",
            onClick = {
                uriHandler.openUri("https://catgpt.wvd.io/")
            }
        )
    }

}