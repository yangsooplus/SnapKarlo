package com.yangsooplus.snapkarlo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prompt(
    @SerialName("text")
    val text: String,
    @SerialName("batch_size")
    val batchSize: Int,
)
