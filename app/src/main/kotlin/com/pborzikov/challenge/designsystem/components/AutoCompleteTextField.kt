package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutoCompleteTextField(
    text: String,
    onValueChange: (String) -> Unit,
    item: @Composable ((T) -> Unit),
    suggestions: List<T>,
    onSuggestionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            label = label,
        )
        if (suggestions.isNotEmpty()) {
            DropdownMenu(
                properties = PopupProperties(focusable = false),
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEachIndexed { index, value ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = {
                            item(value)
                        },
                        onClick = {
                            expanded = false
                            onSuggestionSelected(index)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun AutoCompleteTextField_Preview() {
    AutoCompleteTextField(
        text = "String",
        onValueChange = {},
        label = { Text(text = "label") },
        item = { item ->
            Text(text = item)
        },
        suggestions = listOf("One", "Two"),
        onSuggestionSelected = {},
    )
}
