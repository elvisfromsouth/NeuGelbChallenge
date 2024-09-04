package com.pborzikov.challenge.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.imageLoader

val CoilImageLoader: ImageLoader
    @Composable
    get() = LocalCoilImageLoader.current ?: LocalContext.current.imageLoader

val LocalCoilImageLoader = compositionLocalOf<ImageLoader?> {
    null
}

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}
