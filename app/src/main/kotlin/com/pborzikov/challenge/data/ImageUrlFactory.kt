package com.pborzikov.challenge.data

import coil.size.Size
import coil.size.pxOrElse

class ImageUrlFactory(
    private val baseUrl: String,
    private val sizes: List<String>,
) {

    // Size format: "xY"
    // Where: x - letter, Y - number
    private val intSizes = sizes
        .filter { it.startsWith(IMAGE_WIDTH_PREFIX) }
        .map { it.drop(1).toInt() }


    fun getImageUrl(
        imageUrl: String,
        viewPortSize: Size,
    ): String {
        val viewPortWidth = viewPortSize.width.pxOrElse { -1 }

        val wide = if (viewPortWidth == -1 || viewPortWidth > intSizes.last()) {
            sizes.last()
        } else {
            IMAGE_WIDTH_PREFIX + intSizes.first { viewPortWidth < it }
        }

        return "${baseUrl}$wide$imageUrl"
    }

    companion object {
        const val IMAGE_WIDTH_PREFIX = "w"
    }
}
