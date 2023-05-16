package com.yangsooplus.snapkarlo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("id")
    val id: String,
    @SerialName("model_version")
    val modelVersion: String,
    @SerialName("images")
    val images: List<Image>
)
