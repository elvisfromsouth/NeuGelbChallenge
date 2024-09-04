package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pborzikov.challenge.R


@Composable
fun NextPageLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineSmall,
            text = stringResource(R.string.loading),
        )
    }
}

@Preview
@Composable
fun NextPageLoading_Preview() {
    NextPageLoading()
}
