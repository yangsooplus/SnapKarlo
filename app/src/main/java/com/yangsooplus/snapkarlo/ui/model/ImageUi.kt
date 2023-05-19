package com.yangsooplus.snapkarlo.ui.model

import android.graphics.Bitmap
import java.time.LocalDateTime

data class ImageUi(
    val id: String,
    val keyword: String,
    val bitmap: Bitmap,
    val dateTime: LocalDateTime
)
