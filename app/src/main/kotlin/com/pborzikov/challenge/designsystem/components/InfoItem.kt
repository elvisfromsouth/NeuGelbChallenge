package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InfoItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,

            text = title,
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .widthIn(min = 5.dp),
        )
        Text(
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            text = description,
        )
    }
}

@Preview
@Composable
fun InfoItem_Preview() {
    InfoItem(
        title = "Title",
        description = "Description",
    )
}
