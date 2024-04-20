package org.haidy.servify.presentation.screens.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme

@Composable
fun ItemProfileSection(
    title: String,
    value: String
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)) {
        Text(
            text = title,
            style = Theme.typography.bodyLarge,
            color = Theme.colors.grey300,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = value,
            style = Theme.typography.titleLarge,
            color = Theme.colors.dark100,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())
    }
}