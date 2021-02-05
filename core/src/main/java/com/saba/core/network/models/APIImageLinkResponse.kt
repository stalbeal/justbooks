package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APIImageLinkResponse(
    @SerializedName("smallThumbnail") val smallThumbnail: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("small") val small: String?,
    @SerializedName("medium") val medium: String?,
    @SerializedName("large") val large: String?,
    @SerializedName("extraLarge") val extraLarge: String?
)