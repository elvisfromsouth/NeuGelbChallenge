package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pborzikov.challenge.R


@Composable
fun PageLoadingError(
    errorMessage: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(22.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = errorMessage,
                textAlign = TextAlign.Center,
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = onClick,
            ) {
                Text(
                    text = stringResource(R.string.retry),
                )
            }
        }
    }
}


@Preview
@Composable
fun PageLoadingError_Preview() {
    PageLoadingError(
        errorMessage = "Error message",
        onClick = {},
    )
}
