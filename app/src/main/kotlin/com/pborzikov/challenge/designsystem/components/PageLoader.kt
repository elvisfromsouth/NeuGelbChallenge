package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pborzikov.challenge.R

@Composable
fun PageLoader(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.loading),
        )
    }
}


@Preview
@Composable
fun PageLoader_Preview() {
    PageLoader()
}
