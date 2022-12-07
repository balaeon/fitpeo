package com.balaeon.fitpeotest.data.response

import java.io.Serializable

data class PhotoListResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
): Serializable