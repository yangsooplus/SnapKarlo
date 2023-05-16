package com.yangsooplus.snapkarlo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Koni(
    @SerialName("prompt")
    val prompt: Prompt
)
