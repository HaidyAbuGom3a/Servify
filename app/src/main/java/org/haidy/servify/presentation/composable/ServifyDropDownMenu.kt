package org.haidy.servify.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServifyDropDownMenu(
    onClickItem: (String) -> Unit,
    options: List<String>,
    hint: String,
    selectedText: String,
    modifier: Modifier = Modifier,
    hintColor: Color = Theme.colors.dark300.copy(alpha = 0.7f),
    errorMessage: String = ""
) {
    var isExpanded by remember { mutableStateOf(false) }
    val iconDrawable = if (isExpanded) Resources.images.arrowDropUpIcon else
        Resources.images.arrowDropDownIcon
    Box(
        modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
            ServifyTextField(
                text = selectedText,
                hint = hint,
                onValueChange = onClickItem,
                hintColor = hintColor,
                trailingPainter = painterResource(id = iconDrawable),
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                errorMessage = errorMessage
            )

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .background(Theme.colors.background)
                    .clip(RoundedCornerShape(Theme.radius.large))
                    .background(Theme.colors.grey200)
                    .exposedDropdownSize(),
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        {
                            Text(
                                text = option,
                                style = Theme.typography.bodyLarge,
                                fontSize = 16.sp,
                                color = Theme.colors.dark200
                            )
                        },
                        onClick = { onClickItem(option); isExpanded = false }
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = false)
@Composable
private fun PreviewServifyDropDownMenu() {
    var selectedText by remember { mutableStateOf("") }
    ServifyDropDownMenu(
        onClickItem = { selectedText = it },
        options = listOf("User", "Specialist"),
        hint = "Register as",
        selectedText = selectedText
    )
}