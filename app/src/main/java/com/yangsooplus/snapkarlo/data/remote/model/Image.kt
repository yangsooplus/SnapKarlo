package com.yangsooplus.snapkarlo.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: String,
    @SerialName("nsfw")
    val nsfw: Boolean,
    @SerialName("nsfw_score")
    val nsfwScore: Double
)
