package com.yangsooplus.snapkarlo.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromptData(
    @SerialName("prompt")
    val prompt: Prompt
)

@Serializable
data class Prompt(
    @SerialName("text")
    val text: String,
    @SerialName("batch_size")
    val batchSize: Int
)
