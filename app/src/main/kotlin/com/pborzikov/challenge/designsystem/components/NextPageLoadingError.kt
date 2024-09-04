package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pborzikov.challenge.R


@Composable
fun NextPageLoadingError(
    errorMessage: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 5.dp,
            ),
    ) {
        Text(
            modifier = Modifier
                .weight(1.0f)
                .align(Alignment.CenterVertically),
            text = errorMessage,
            fontStyle = FontStyle.Italic,
        )
        Button(
            modifier = Modifier
                .align(Alignment.Top),
            onClick = onClick,
        ) {
            Text(
                text = stringResource(R.string.retry),
            )
        }
    }
}

@Preview
@Composable
fun NextPageLoadingError_Preview() {
    NextPageLoadingError(
        errorMessage = "Error message",
        onClick = {},
    )
}
