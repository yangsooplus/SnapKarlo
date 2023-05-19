package com.yangsooplus.snapkarlo.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class T2iResponse(
    @SerialName("id")
    val id: String,
    @SerialName("model_version")
    val modelVersion: String,
    @SerialName("images")
    val images: List<Image>
)
